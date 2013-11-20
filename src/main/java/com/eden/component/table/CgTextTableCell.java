package com.eden.component.table;

import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;

import com.eden.component.text.CgTooltip;
import com.eden.fxmvc.util.TypeConvertUtil;

public class CgTextTableCell<S> extends CgTableCell<S, String> {
	private TextField textField;

	public CgTextTableCell() {
		super();
		textField = new TextField();
		textField.setEditable(false);
		textField.getStyleClass().add("cellTextArea");

	}

	@Override
	public void updateItem(String item, boolean empty) {
		super.updateItem(item, empty);
		if (empty) {
			setText(null);
			setGraphic(null);
		} else {
			textField.setText(TypeConvertUtil.toStr(item));
			if(item != null && item.length() > 5){
				Tooltip tip = new CgTooltip(TypeConvertUtil.toStr(item));
				textField.setTooltip(tip);
			}
			setGraphic(textField);
		}

	}
	/*
	 * @Override public void startEdit() { super.startEdit(); if (textField ==
	 * null) { createTextField(); } setText(null); setGraphic(textField);
	 * textField.selectAll(); }
	 * 
	 * @Override public void cancelEdit() { super.cancelEdit(); setText((String)
	 * getItem()); setGraphic(null);
	 * 
	 * }
	 */
	/*
	 * private void createTextField() { textField = new
	 * CgTextFieldLabel(getString() , 60 , 25);
	 * textField.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
	 * textField.setOnKeyReleased(new EventHandler<KeyEvent>() {
	 * 
	 * @Override public void handle(KeyEvent t) { if (t.getCode() ==
	 * KeyCode.ENTER) { commitEdit((T)textField.getText()); } else if
	 * (t.getCode() == KeyCode.ESCAPE) { cancelEdit(); } } }); }
	 */
}
