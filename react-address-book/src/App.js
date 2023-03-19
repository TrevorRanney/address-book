
import './App.css';

import React, { useState } from 'react';
import addressBook from './address-book.json';
import TableView from './address-table';
import CardView from './address-cards';

const App = () => {
	const showCardView = 'Show Card View'
	const showTableView = 'Show Table View'
	const [nextView, changeView] = useState(showCardView)
	const [data] = useState(addressBook.AddressBook.Contact)

	const handleClick = () => {
		switch(nextView){
			case showTableView:
				changeView(showCardView);
				break;
			default:
				changeView(showTableView);
		}
	};

	return (
		<div className='App'>
			<button onClick={handleClick}>{nextView}</button>
			{nextView === showCardView ? <TableView addressBook={data} /> : <CardView addressBook={data} />}
		</div>
	);
}

export default App;
