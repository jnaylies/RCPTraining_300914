package com.sogeti.rental.adapters;

import org.eclipse.core.runtime.IAdapterFactory;

import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.Rental;

public class RentalAdapterFactory implements IAdapterFactory {

	@Override
	public Object getAdapter(Object adaptableObject, Class adapterType) {
		if ((adaptableObject instanceof Rental) && (adapterType == Customer.class)) {
			return ((Rental)adaptableObject).getCustomer();
		}
		return null;
	}

	@Override
	public Class[] getAdapterList() {
		return new Class[] { Customer.class };
	}

}
