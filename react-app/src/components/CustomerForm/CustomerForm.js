import { useContext, useEffect, useState } from "react";
import { CustomerContext } from "../../services/CustomerService";
import findCep from "../../utils/ViaCep";

const ADDRESS_NOT_FOUND = 0; // tried to search and didn't find
const ADDRESS_UNAVAILABLE = 1; // didn't search yet
const ADDRESS_FOUND = 2; // did search and found

const CustomerForm = () => {

    const { currentCustomer, addCustomer, editCustomer } = useContext( CustomerContext );
    const [ address, updateAddress ] = useState({});
    const [ addressStatus, setAddressStatus ] = useState(ADDRESS_UNAVAILABLE);
    
    let customerData = {
        id: null,
        name: '',
        surname: '',
        cep: '',
    };
    
    useEffect(() => {
        if(currentCustomer.getId){
            customerData.id = currentCustomer.getId();
            customerData.name = currentCustomer.getName();
            customerData.surname = currentCustomer.getSurname();
            customerData.cep = currentCustomer.getCep();
            updateAddress(currentCustomer.getAddress());
            setAddressStatus(ADDRESS_FOUND);
            updateForm({...customerData});
        }
    }, [currentCustomer])
    
    const [form, updateForm] = useState(customerData);
    
    const onSubmit = (e) => {
        if(form.id){
            editCustomer(form.id, form.name, form.surname, form.cep);
        }else{
            addCustomer(form.name, form.surname, form.cep);
        }
        e.preventDefault();
    }

    const cancelEdit = (e) => {
        updateForm(customerData);
        e.preventDefault();
    }

    const setForm = (e) => {
        const val = e.target.value;
        const newForm = { ...form };
        newForm[e.target.name] = val;
        updateForm(newForm);
    }

    const searchCep = async (e) => {
        const cep = e.target.value.replace(/\D/g, "");
        if(cep.length === 8){
            try{
                const address = await findCep(cep);
                if(address.erro){
                    setAddressStatus(ADDRESS_NOT_FOUND);
                    console.info(`it wasn't possible to find cep ${cep}`);
                }else{
                    setAddressStatus(ADDRESS_FOUND);
                    updateAddress(address);
                }
            }catch(e){
                console.error(e);
            }
        }
        e.preventDefault();
    }

    const SubmitButton = () => {
        if(form.id){
            return (
                <>
                    <input type="submit" value="edit customer" />
                    <input type="button" value="cancel" onClick={cancelEdit} />
                </>
            );
        }
        return (<input type="submit" value="new customer" />);
    }

    const AddressInfo = () => {

        if(addressStatus === ADDRESS_FOUND){
            return (<div className={ 'address address-found' }>
                { address.logradouro }, { address.bairro }, { address.localidade }
            </div>);
        }

        if(addressStatus === ADDRESS_NOT_FOUND){
            return (<div className={ 'address address-not-found' }>Address not found!</div>);
        }

        return;
    }

    return (
        <section>
            <form onSubmit={onSubmit}>
                <label>
                    Name:
                    <input type="text" name="name" value={form.name} onChange={setForm} />
                </label>
                <label>
                    Surname:
                    <input type="text" name="surname" value={form.surname} onChange={setForm} />
                </label>
                <label>
                    CEP:
                    <input type="text" name="cep" value={form.cep} onBlur={searchCep}  onKeyUp={searchCep} onChange={setForm} />
                </label>
                <SubmitButton />
            </form>
            <AddressInfo />
        </section>
    );

}

export default CustomerForm;