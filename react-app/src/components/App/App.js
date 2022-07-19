import '../../App.css';
import CustomerForm from '../CustomerForm/CustomerForm';
import CustomerList from '../CustomerList/CustomerList';
import CustomerService from '../../services/CustomerService';

function App() {
  return (
    <div className="app">
        <header className="app-header">
          <div className="layout">
            <h1>Customer Repo</h1>
            <p>
              Customer Repo is an application developed for the DIO/TQI bootcamp challenge. This react app aims to go beyond the
              original proposal of having only a Spring Framework Web API and provides a simple client to interact with the server.
            </p>
            <p>
              CEP in Brazil is the equivalent of a Zip Code in the US and they can be accessed in the <a href="https://viacep.com.br/">ViaCEP API</a>,
              or the code <span className="code">01001000</span> can be used for testing.
            </p>
          </div>
        </header>
        <CustomerService>
          <CustomerForm />
          <CustomerList />
        </CustomerService>
        <footer>
          <div className="layout">
            Developed by Eduardo Augusto da Silva Leite [ <a target="_blank" href="https://dsleite.com.br">dsleite.com.br</a> | <a target="_blank" href="https://github.com/eduardodsl/">github</a> ]
          </div>
        </footer>
      </div>
  );
}

export default App;
