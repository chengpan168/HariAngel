package com.eden.component.table;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.StackPane;
import javafx.scene.text.TextAlignment;

public class CgTableTitle extends StackPane{
	public <S, T> CgTableTitle(String text, final TableColumn<S, T> column) {
		Label label = new Label(text);
		label.setStyle("-fx-padding: 0px;-fx-cursor:hand");
		label.setWrapText(true);
		label.setAlignment(Pos.CENTER);
		label.setTextAlignment(TextAlignment.CENTER);
		getChildren().add(label);
		prefWidthProperty().bind(column.widthProperty());
		label.prefWidthProperty().bind(prefWidthProperty());
	}
}
