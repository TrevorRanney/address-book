import React from 'react';

const AddressCardsComponent = ({ addressBook }) => {
	return (
        <div>
            <h1>Contact Cards:</h1>
            <div class="outer">
                {addressBook.map((contact) => (
                    <div class="inner">
                        <div key={contact.CustomerID} className="card">
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
