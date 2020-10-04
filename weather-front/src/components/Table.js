import React, { Component } from 'react'

class Table extends Component {
   
   constructor(props) {
      super(props) 
      this.state = { 
         headers:[
          "id", "Server", "City", "Temperature", "Humidity", "Pressure", "Date" 
         ],
         servers: [
            "gridforecast", "openweathermap"
         ]
      }
   }

   renderTableHeader() {
      return this.state.headers.map((key, index) => {    
        return <th key={index}>{key}</th>
      })
    }

   renderTableData() {
    return this.props.data.map((record, index) => {
       const { id, serverId, cityName, temperature, humidity, pressure, dateCreated} = record 
       return (
          <tr key={id}>
             <td>{id}</td>
             <td>{this.state.servers[serverId]}</td>
             <td>{cityName}</td>
             <td>{temperature}</td>
             <td>{humidity}</td>
             <td>{pressure}</td>
             <td>{dateCreated}</td>
          </tr>
       )
    })
 }

 render() {
    return (
       <div>         
          <div className='title'>   
            <span className="header">Weather Statistic</span>         
          </div>
          <table id='weather'>
            <thead>
                <tr>{this.renderTableHeader()}</tr>
            </thead>
            <tbody>          
                {this.renderTableData()}
            </tbody>
          </table>
       </div>
    )
 }
}

export default Table