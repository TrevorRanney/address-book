import React from 'react';

const AddressCardsComponent = ({ addressBook }) => {
	return (
        <div className='contacts'>
            <div className="outer">
                {addressBook.map((contact) => (
                    <div className="inner" key={contact.CustomerID}>
                        <div className="card">
                            <h2>{contact.ContactName}</h2>
                            <p>Title: {contact.ContactTitle}</p>
                            <p>Company Name:  {contact.CompanyName}</p>
                            <p>Email: {contact.Email}</p>
                            <p>Phone: {contact.Phone}</p>
                            <p>Address: {contact.Address}
                                &nbsp;{contact.City}
                                &nbsp;{contact.PostalCode}
                                &nbsp;{contact.Country}
                            </p>
                            <p>Region: {contact.Region}</p>
                            <p>Fax: {contact.Fax}</p>
                            <p>ID: {contact.CustomerID}</p>
                        </div>
                    </div>
                ))}
            </div>
        </div>
	);
}

export default AddressCardsComponent;
