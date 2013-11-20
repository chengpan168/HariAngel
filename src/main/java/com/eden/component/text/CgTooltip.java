package com.eden.component.text;

import javafx.scene.control.Tooltip;

public class CgTooltip extends Tooltip {
	public CgTooltip(String text) {
		super(text) ;
		setWrapText(true);
		setPrefWidth(150);
	}
}
