package com.sogeti.rental.ui.views;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import com.opcoach.training.rental.RentalAgency;
import com.sogeti.rental.core.RentalCoreActivator;

public class RentalAgencyView extends ViewPart {

	public RentalAgencyView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		// TODO Auto-generated method stub
		TreeViewer tv = new TreeViewer(parent);
		RentalProvider rp = new RentalProvider();
		tv.setContentProvider(rp);
		tv.setLabelProvider(rp);
		Collection<RentalAgency> agencies = new ArrayList<RentalAgency>();
		agencies.add(RentalCoreActivator.getAgency());
		tv.setInput(agencies);
		
		getSite().setSelectionProvider(tv);
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
