import React from 'react';

const ContactTable = ( {addressBook} ) => {
  return (
    <div>
      <h1>Contact Table:</h1>
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
