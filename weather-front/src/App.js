import React from 'react';
import Table from './components/Table';

class App extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      error: null,
      isLoaded: false,
      items: []
    };
  }

  fetchData = () => {
    fetch("http://localhost:8080/weather/api/getall")
      .then(res => res.json())
      .then(
      (result) => {
          this.setState({
          isLoaded: true,
          items: result
          });
      },
      (error) => {
          this.setState({
          isLoaded: true,
          error
          });
      }
      )
  }

  componentDidMount() {
    this.fetchData();
  }
  
  render() {
      const { error, isLoaded, items } = this.state;
      if (error) {
          return <div>Ошибка: {error.message}</div>;
      } else if (!isLoaded) {
          return <div>Загрузка...</div>;
      } else {
        return (
          <div>     
            <button className="button" onClick={this.fetchData}>Refresh</button>       
            <Table data={items}/>
          </div>
        )
      }
  }
};

export default App;
