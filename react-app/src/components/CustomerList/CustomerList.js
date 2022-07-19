import React, { useContext } from 'react';
import { CustomerContext } from "../../services/CustomerService";

const CustomerList = () => {

    const { customerList, setCurrentCustomer, deleteCustomer } = useContext( CustomerContext );

    const Customer = ({ data }) => {

        const { cep, logradouro, bairro, localidade, uf } = data.getAddress();

        return (
            <div id={ data.getId() } className="customer">
                <div className="data">
                    <div className="name"><strong>Name:</strong> { data.getFullName() }</div>
                    <button onClick={ () => setCurrentCustomer(data) }>Edit</button>
                    <button onClick={ () => deleteCustomer(data.getId()) }>Delete</button>
                </div>
                <div className="address">
                    <strong>Address:</strong> { cep }, { logradouro }, { bairro }, { localidade }, { uf }
                </div>
            </div>
        );
    }

    return(
        <section id="customer-list">
            <div className="layout">
                {
                    (customerList.length > 0 && (
                        <ul>
                        {
                            customerList.map((data, index) => {
                                return <li key={ index }><Customer data={ data } /></li>
                            })
                        }
                        </ul>
                    ))
                }
                {
                    (customerList.length == 0 && (
                        <div className={'no-customers'}>No Customers</div>
                    ))
                }
            </div>
        </section>
    );

}

export default CustomerList;