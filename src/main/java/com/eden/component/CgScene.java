package com.eden.component;

import java.util.Arrays;

import com.eden.fxmvc.constant.FXConstant;

import javafx.scene.Parent;
import javafx.scene.Scene;

public class CgScene extends Scene {

	public CgScene(Parent node, double width, double heigth) {
		super(node, width, heigth);
		getStylesheets().addAll(Arrays.asList(FXConstant.STYLE_SHEETS));
	}

}
