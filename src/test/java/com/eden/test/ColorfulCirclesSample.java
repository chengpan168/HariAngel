package com.eden.test;

/**
 * Copyright (c) 2008, 2012 Oracle and/or its affiliates.
 * All rights reserved. Use is subject to license terms.
 */
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.BoxBlur;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.List;
import static java.lang.Math.random;

/**
 * A sample that demonstrates how to draw and paint shapes, apply visual
 * effects, blend colors in overlapping objects, and animate objects.
 *
 * @see javafx.scene.effect.BlendMode
 * @see javafx.scene.effect.BoxBlur
 * @see javafx.scene.shape.Circle
 * @see javafx.scene.Group
 * @see javafx.scene.paint.LinearGradient
 * @see javafx.animation.Timeline
 */
public class ColorfulCirclesSample extends Application {
    private static final double WIDTH = 450, HEIGHT = 480;
    private Timeline animation;

    private void init(Stage primaryStage) {
        Group root = new Group();
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 495,480));
        Group layer1 = new Group();
        for(int i=0; i<15;i++) {
            Circle circle = new Circle(200,Color.web("white",0.05f));
            circle.setStrokeType(StrokeType.OUTSIDE);
            circle.setStroke(Color.web("white",0.2f));
            circle.setStrokeWidth(4f);
            layer1.getChildren().add(circle);
        }
        // create second list of circles
        Group layer2 = new Group();
        for(int i=0; i<20;i++) {
            Circle circle = new Circle(70,Color.web("white",0.05f));
            circle.setStrokeType(StrokeType.OUTSIDE);
            circle.setStroke(Color.web("white",0.1f));
            circle.setStrokeWidth(2f);
            layer2.getChildren().add(circle);
        }
        // create third list of circles
        Group layer3 = new Group();
        for(int i=0; i<10;i++) {
            Circle circle = new Circle(150,Color.web("white",0.05f));
            circle.setStrokeType(StrokeType.OUTSIDE);
            circle.setStroke(Color.web("white",0.16f));
            circle.setStrokeWidth(4f);
            layer3.getChildren().add(circle);
        }
        // Set a blur effect on each layer
        layer1.setEffect(new BoxBlur(30,30,3));
        layer2.setEffect(new BoxBlur(2,2,2));
        layer3.setEffect(new BoxBlur(10,10,3));
        // create a rectangle size of window with colored gradient
        Rectangle colors = new Rectangle(WIDTH, HEIGHT,
                new LinearGradient(0f,1f,1f,0f,true, CycleMethod.NO_CYCLE, new Stop(0,Color.web("#f8bd55")),
                        new Stop(0.14f,Color.web("#c0fe56")),
                        new Stop(0.28f,Color.web("#5dfbc1")),
                        new Stop(0.43f,Color.web("#64c2f8")),
                        new Stop(0.57f,Color.web("#be4af7")),
                        new Stop(0.71f,Color.web("#ed5fc2")),
                        new Stop(0.85f,Color.web("#ef504c")),
                        new Stop(1,Color.web("#f2660f")))
        );
        colors.setBlendMode(BlendMode.OVERLAY);
        // create main content
        Group group = new Group(
                new Rectangle(WIDTH, HEIGHT, Color.BLACK),
                layer1, 
                layer2,
                layer3,
                colors
        );
        Rectangle clip = new Rectangle(WIDTH, HEIGHT);
        clip.setSmooth(false);
        group.setClip(clip);
        root.getChildren().add(group);
        // create list of all circles
        List<Node> allCircles = new ArrayList<Node>();
        allCircles.addAll(layer1.getChildren());
        allCircles.addAll(layer2.getChildren());
        allCircles.addAll(layer3.getChildren());
        // Create a animation to randomly move every circle in allCircles
        animation = new Timeline();
        for(Node circle: allCircles) {
            animation.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO, // set start position at 0s
                    new KeyValue(circle.translateXProperty(),random()*WIDTH),
                    new KeyValue(circle.translateYProperty(),random()*HEIGHT)
                ),
                new KeyFrame(new Duration(40000), // set end position at 40s
                    new KeyValue(circle.translateXProperty(),random()*WIDTH),
                    new KeyValue(circle.translateYProperty(),random()*HEIGHT)
                )
            );
        }
        animation.setAutoReverse(true);
        animation.setCycleCount(Animation.INDEFINITE);
    }

    @Override public void stop() {
        animation.stop();
    }

    public void play() {
        animation.play();
    }

    public double getSampleWidth() { return 495; }

    public double getSampleHeight() { return 480; }

    @Override public void start(Stage primaryStage) throws Exception {
        init(primaryStage);
        primaryStage.show();
        play();
    }
    public static void main(String[] args) { launch(args); }
}
