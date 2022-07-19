import { createContext, useEffect, useState } from 'react';
import { deleteJson, getJson, postJson, putJson } from '../utils/Web';

export const CustomerContext = createContext();
const DEFAULT_URL = 'http://localhost:8080';

class Customer {

    #id = "";
    #name = "";
    #surname = "";
    #address = {};
    #removed = false;

    constructor(id, name, surname){
        this.#id = id;
        this.#name = name;
        this.#surname = surname;
        this.#address = {};
        this.#removed = false;
    };

    getId(){
        return this.#id;
    }

    getName(){
        return this.#name;
    }

    getSurname(){
        return this.#surname;
    }

    getCep(){
        return this.#address.cep;
    }

    getFullName(){
        return this.#name + " " + this.#surname;
    }

    addAddress(address){
        this.#address = address;
    }

    getAddress(){
        return this.#address;
    }

    isRemoved(){
        return this.#removed;
    }

}

const CustomerService = ({children}) => {

    const [ customerList, setCustomerList ] = useState([]);
    const [ currentCustomer, setCurrentCustomer ] = useState({});

    const makeCustomer = (id, name, surname, address = null) => {
        const customer = new Customer(id, name, surname);
        if(address) customer.addAddress(address);
        return customer;
    }

    const addCustomer = async (name, surname, cep) => {
        const data = new URLSearchParams();
        data.append('name', name);
        data.append('surname', surname);
        data.append('cep', cep);
        await postJson(`${DEFAULT_URL}/customers/`, data);
        loadCustomers();
    }

    const editCustomer = async (id, name, surname, cep) => {
        const data = new URLSearchParams();
        data.append('id', id);
        data.append('name', name);
        data.append('surname', surname);
        data.append('cep', cep);
        await putJson(`${DEFAULT_URL}/customers/${id}`, data);
        loadCustomers();
    }

    const deleteCustomer = async(id) => {
        await deleteJson(`${DEFAULT_URL}/customers/${id}`);
        loadCustomers();
    }

    const loadCustomers = async () => {
        
        try{
            const request = await getJson(`${DEFAULT_URL}/customers/`);
            if(request.success && request.data.length > 0){
                const customers = [];
                request.data.forEach(el => {
                    const { id, name, surname, address } = el;
                    const customer = makeCustomer(id, name, surname, address);
                    customers.push(customer);
                });
                setCustomerList(customers);
                return customerList;
            }
        } catch(e){
            console.error(e);
        }

    }

    useEffect(() => {
        loadCustomers();
    }, []);

    return (
        <CustomerContext.Provider value={{
            currentCustomer,
            setCurrentCustomer,
            customerList,
            addCustomer,
            editCustomer,
            deleteCustomer
        }}>
            { children }
        </CustomerContext.Provider>
    );

}

export default CustomerService;