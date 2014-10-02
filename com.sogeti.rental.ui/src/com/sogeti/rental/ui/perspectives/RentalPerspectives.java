package com.sogeti.rental.ui.perspectives;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class RentalPerspectives implements IPerspectiveFactory {

	/**
	 * Creates the initial layout for a page.
	 */
	public void createInitialLayout(IPageLayout layout) {
		layout.setEditorAreaVisible(false);
		String editorArea = layout.getEditorArea();
		addFastViews(layout);
		addViewShortcuts(layout);
		addPerspectiveShortcuts(layout);
		layout.addView("com.sogeti.rental.ui.rentalPropertyView", IPageLayout.TOP, 0.3f, IPageLayout.ID_EDITOR_AREA);
		layout.addView("com.sogeti.rental.ui.rentalAgencyView", IPageLayout.TOP, 0.4f, IPageLayout.ID_EDITOR_AREA);
		layout.addView("com.sogeti.rental.ui.rentalCustomerView", IPageLayout.TOP, 0.3f, IPageLayout.ID_EDITOR_AREA);
	}

	/**
	 * Add fast views to the perspective.
	 */
	private void addFastViews(IPageLayout layout) {
	}

	/**
	 * Add view shortcuts to the perspective.
	 */
	private void addViewShortcuts(IPageLayout layout) {
	}

	/**
	 * Add perspective shortcuts to the perspective.
	 */
	private void addPerspectiveShortcuts(IPageLayout layout) {
	}

}
