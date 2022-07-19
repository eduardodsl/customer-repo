import React, { useContext } from 'react';
import { CustomerContext } from "../../services/CustomerService";

const CustomerList = () => {

    const { customerList, setCurrentCustomer, deleteCustomer } = useContext( CustomerContext );

    const Customer = ({ data }) => {
        return (
            <div id={ data.getId() } className={'customer'}>
                { data.getFullName() }
                <button onClick={ () => setCurrentCustomer(data) }>Edit</button>
                <button onClick={ () => deleteCustomer(data.getId()) }>Delete</button>
            </div>
        );
    }

    return(
        <section>
            {
                (customerList.length > 0 && (
                    customerList.map((data, index) => {
                        return <Customer key={ index } data={ data } />
                    })
                ))
            }
            {
                (customerList.length == 0 && (
                    <div className={'no-customers'}>No Customers</div>
                ))
            }
        </section>
    );

}

export default CustomerList;