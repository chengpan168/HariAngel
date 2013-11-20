package com.eden.component.page;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.eden.constant.ConstantMsg;
import com.eden.fxmvc.dao.support.Page;
import com.eden.fxmvc.util.TypeConvertUtil;
import com.eden.view.MessageBox;

public class Pagination extends HBox {
	private static Log log = LogFactory.getLog(Pagination.class) ;
	
	private Page page ;
	
	private Callback<Page , Object> callback ;
	
	private Button prevButton ;
	private Button nextButton ;
	private ImageView goButton ;
	private TextField currPageField ;
	private Label pageCountField ;
	private ChoiceBox<Integer> pageSizeBox ;
	
	public Pagination(){
		super(5) ;
		this.page = new Page() ;
		init();
	}
	
	public Pagination(int total) {
		super(5) ;
		this.page = new Page() ;
		page.setTotal(total) ;
		init();
	}
	public Pagination(int total , Callback<Page , Object> callback ) {
		super(5) ;
		this.callback = callback ;
		this.page = new Page() ;
		page.setTotal(total) ;
		init();
	}
	
	public Pagination(Page page) {
		super(5) ;
		this.page = page ;
		init();
	}
	
	public void init(){
		this.setPrefSize(120, 35) ;
		this.setMaxSize(120, 30) ;
		Image image = new Image("css/img/prev.png") ;
		ImageView imageView = new ImageView(image) ;
		prevButton = new Button("" , imageView) ;
		prevButton.getStyleClass().add("page_prev") ;
		prevButton.setOnMouseClicked(new EventHandler<Event>() {
			public void handle(Event e){
				prevPage(e) ;
			}
		}) ;
		
		image = new Image("css/img/next.png") ;
		imageView = new ImageView(image) ;
		nextButton = new Button("" ,imageView) ;
		nextButton.getStyleClass().add("page_next") ;
		nextButton.setOnMouseClicked(new EventHandler<Event>() {
			public void handle(Event e) {
				nextPage(e) ;
			}
		}) ;
		
		currPageField = new TextField(page.getCurrPage() + "") ;
		currPageField.setMinSize(60, 20) ;
		currPageField.getStyleClass().add("page_curr") ;
		currPageField.setAlignment(Pos.BASELINE_RIGHT) ;
		currPageField.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent e) {
						currPage(e) ;
					}
		}) ;
		
		pageCountField = new Label("/" + page.getPageCount()) ;
		pageCountField.setMinSize(40, 20) ;
		
		image = new Image("css/img/go.png") ;
		goButton = new ImageView(image) ;
		goButton.setCursor(Cursor.HAND) ;
		goButton.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event e) {
				currPage(e);
			}
		}) ;
		
		//改变分页大小时，what do you want handle?
		ChangeListener<Integer> pageSizeChangeListener = new ChangeListener<Integer>() {
			@Override
			public void changed( ObservableValue<? extends Integer> paramObservableValue,
					Integer oldValue, Integer newValue) {
				log.debug("page size change to " + newValue) ;
				currPage(null) ;
			}
		};
			
		pageSizeBox = new ChoiceBox<Integer>() ;
		pageSizeBox.setItems(FXCollections.observableArrayList(10 , 20 , 30 , 50)) ;
		pageSizeBox.setValue(20) ;
		pageSizeBox.getStyleClass().add("page_size") ;
		pageSizeBox.setMinSize(50, 20) ;
		pageSizeBox.getSelectionModel().selectedItemProperty().addListener(pageSizeChangeListener) ;
		
		if(page.getCurrPage() <= 1){
			prevButton.setDisable(true) ;
		}
		if(page.getCurrPage() >= page.getPageCount()){
			nextButton.setDisable(true) ;
		}
		pageSizeBox.setValue(page.getPageSize()) ;
		
		this.getChildren().addAll(pageSizeBox , prevButton ,currPageField ,pageCountField , nextButton , goButton) ;
	}
	
	public void currPage(Event e) {
		int start = TypeConvertUtil.toInt(currPageField.getText()) ;
		log.debug("jump to page " + start) ;
		if(start < 1 || start > page.getPageCount()  ){
			MessageBox.showMessage( ConstantMsg.PAGE_CURR_ERR ) ;
			update(page) ;
			
		} else {
			page.setCurrPage(start) ;
			page.setPageSize(pageSizeBox.getSelectionModel().getSelectedItem()) ;
			processEvent(page) ;
		}
	}
	
	public void prevPage(Event e){
		int currPage = page.getCurrPage() - 1 ;
		log.debug("prev page " + currPage) ;
		if(currPage < 1){
			return ;
		} else {
			page.setCurrentPage(currPage) ;
			processEvent(page) ;
		}
	}
	public void nextPage(Event e){
		int start = TypeConvertUtil.toInt(currPageField.getText()) +  1 ;
		log.debug("jump to page " + start) ;
		if(start > page.getPageCount()){
			return ;
		} else {
			page.setCurrentPage(start) ;
			processEvent(page) ;
		}
	}
	
	public void update(Page newPage) {
		int currPage = newPage.getCurrPage() ;
		int pageCount = newPage.getPageCount() ;
		
		currPageField.setText(currPage+"") ;
		pageCountField.setText("/"+pageCount) ;
		pageSizeBox.setValue(page.getPageSize()) ;
		
		if(currPage < 2){
			if(!prevButton.isDisable()){
				prevButton.setDisable(true) ;
			}
		} else {
			if(prevButton.isDisable()){
				prevButton.setDisable(false) ;
			}
		}
		
		if(currPage >= pageCount){
			if(!nextButton.isDisable()){
				nextButton.setDisable(true) ;
			} 
		}else {
			if(nextButton.isDisable()){
				nextButton.setDisable(false) ;
			}
		}
	}
	
	public void processEvent(Page newPage){
		update(newPage) ;
		
		jumpTo(newPage) ;
		
	}
	
	public void jumpTo(Page newpage){
		if(callback!=null){
			callback.call(newpage) ;
		}
	}
	
	public int getCurrPage(){
		return page.getCurrPage() ;
	}
	
	public int getPageSize(){
		return page.getPageSize() ;
	}
	
	public int getPageCount(){
		return page.getPageCount()  ;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
		update(page) ;
	}

	public Callback<Page, Object> getCallback() {
		return callback;
	}

	public void setCallback(Callback<Page, Object> callback) {
		this.callback = callback;
	}
	
	public void setTotal(int total){
		page.setTotal(total) ;
//		currPageField.setText(TypeConvertUtil.toStr(page.getCurrPage()) ) ;
//		pageCountField.setText(TypeConvertUtil.toStr(page.getPageCount())) ;
		update(page) ;
	}
	
}
