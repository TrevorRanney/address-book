
import './App.css';

import React, { useState } from 'react';
import addressBook from './address-book.json';
import TableView from './address-table';
import CardView from './address-cards';
import LightSwitch from './light-switch';

const App = () => {
	const showCardView = 'Show Card View';
	const showTableView = 'Show Table View';
	const [nextView, changeView] = useState(showCardView);
	const [data, setData] = useState(addressBook.AddressBook.Contact);
	const [inputValue, setInputValue] = useState('');
	const [light, setLight] = useState(false);

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

	const toggleLight = () => {
		setLight(!light)
	}

	return (
		<div className={light ? 'light' : 'dark'}>
			<div className='filter'>
				<label>Filter</label><input type="text" value={inputValue} onChange={filterContacts} />
				<button onClick={handleClick}>{nextView}</button>
			</div>
			<LightSwitch isOn={light} onToggle={toggleLight} />
			{nextView === showCardView ? <TableView addressBook={data} /> : <CardView addressBook={data} />}
		</div>
	);
}

export default App;
