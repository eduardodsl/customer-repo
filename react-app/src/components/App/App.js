import '../../App.css';
import CustomerForm from '../CustomerForm/CustomerForm';
import CustomerList from '../CustomerList/CustomerList';
import CustomerService from '../../services/CustomerService';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <h1>Customer Repo</h1>
      </header>
      <CustomerService>
        <CustomerForm />
        <CustomerList />
      </CustomerService>
      <footer>
        rodapé
      </footer>
    </div>
  );
}

export default App;
