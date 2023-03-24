import React, { useState } from 'react';

const ContactTable = ( props ) => {
	const [addressBook, setData] = useState(props.addressBook);
	const [count, setCount] = useState(0);

	function fetchData() {
		// This could an API call to server to fetch data
		// one way to do it would be to pass the last customer you have in the last and return the next set
		// or an index could be used
		// console.log("last contact", addressBook[addressBook.length-1])
		setData(prevData => [...prevData,
		{
			"CustomerID": "NEW "+count,
			"CompanyName": "Company"+count,
			"ContactName": "Contact"+count,
			"ContactTitle": "Title"+count,
			"Address": "Address"+count,
			"City": count+"city",
			"Email": count+"dummy@gmail.com",
			"PostalCode": "00000",
			"Country": "USA",
			"Phone": "030-0074321",
			"Fax": "030-0076545"
		}
		]);
		setCount(count + 1);
	}

	function handleScroll(e) {
	  const { scrollTop, clientHeight, scrollHeight } = e.target;
	  if (scrollTop + clientHeight === scrollHeight) {
		fetchData();
	  }
	}

  return (
    <div className='contacts' onScroll={handleScroll}>
		<table>
			<thead>
			<tr>
				<th>Customer ID</th>
				<th>Company Name</th>
				<th>Contact Name</th>
				<th>Contact Title</th>
				<th>Address</th>
				<th>City</th>
				<th>Email</th>
				<th>Postal Code</th>
				<th>Region</th>
				<th>Country</th>
				<th>Phone</th>
				<th>Fax</th>
			</tr>
			</thead>
			<tbody>
				{addressBook.map((contact) => (
				<tr key={contact.CustomerID}>
					<td>{contact.CustomerID}</td>
					<td>{contact.CompanyName}</td>
					<td>{contact.ContactName}</td>
					<td>{contact.ContactTitle}</td>
					<td>{contact.Address}</td>
					<td>{contact.City}</td>
					<td>{contact.Email}</td>
					<td>{contact.PostalCode}</td>
					<td>{contact.Region}</td>
					<td>{contact.Country}</td>
					<td>{contact.Phone}</td>
					<td>{contact.Fax}</td>
				</tr>
       		))}
		</tbody>
      </table>
    </div>
  );
}

export default ContactTable;
