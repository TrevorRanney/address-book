
import './App.css';

import React, { useState } from 'react';
import addressBook from './address-book.json';
import TableView from './address-table';
import CardView from './address-cards';

const App = () => {
	const showCardView = 'Show Card View'
	const showTableView = 'Show Table View'
	const [nextView, changeView] = useState(showCardView)
	const [data, setData] = useState(addressBook.AddressBook.Contact);
	const [inputValue, setInputValue] = useState('');

	const handleClick = () => {
		switch(nextView){
			case showTableView:
				changeView(showCardView);
				break;
			default:
				changeView(showTableView);
		}
	};

	const filterContacts = (event) => {
		setInputValue(event.target.value);
		const data = addressBook.AddressBook.Contact.filter(contact => {
			for(let prop in contact){
				if(contact[prop].toLowerCase().includes(event.target.value.toLowerCase())){
					return true;
				}
			}
			return false;
		})
		setData(data);
	};

	return (
		<div className='App'>
			<label>Filter</label><input type="text" value={inputValue} onChange={filterContacts} />
			<button onClick={handleClick}>{nextView}</button>
			{nextView === showCardView ? <TableView addressBook={data} /> : <CardView addressBook={data} />}
		</div>
	);
}

export default App;
