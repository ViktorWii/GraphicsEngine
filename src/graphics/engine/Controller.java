package graphics.engine;

import graphics.elements.Figure;
import graphics.elements.Point;
import graphics.elements.Polygon;
import graphics.elements.View;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class Controller {
    public Slider LocationX;
    public Slider LocationY;
    public Slider LocationZ;

    public Slider WorldRotationX;
    public Slider WorldRotationY;
    public Slider WorldRotationZ;

    public Slider LocalRotationX;
    public Slider LocalRotationY;
    public Slider LocalRotationZ;

    public Slider ScaleX;
    public Slider ScaleY;
    public Slider ScaleZ;
    public Slider ScaleH;

    public Slider LocalScaleX;
    public Slider LocalScaleY;
    public Slider LocalScaleZ;
    public Slider LocalScaleH;


    public ChoiceBox ViewMode;
    public ChoiceBox SelectedPyramid;

    public CheckBox InvisibleEdges;

    public Button SaveButton;



    public Figure activeFigure;

    public Scene3D scene3D;


    public void saveToFile() {
        try {
            Writer writer = new FileWriter("figure.txt");

            // Количество вершин
            writer.write(Integer.toString(activeFigure.vertices.size()));
            writer.write(System.getProperty("line.separator"));

            // Координаты вершин
            for (Point vertex : activeFigure.vertices) {
                writer.write(Double.toString(vertex.x));
                writer.write(", ");
                writer.write(Double.toString(vertex.y));
                writer.write(", ");
                writer.write(Double.toString(vertex.z));
                writer.write(";");
                writer.write(System.getProperty("line.separator"));
            }



            // Количество граней
            writer.write(Integer.toString(activeFigure.polygons.size()));
            writer.write(System.getProperty("line.separator"));

            // Индексы вершин в гранях
            for (Polygon polygon : activeFigure.polygons) {
                writer.write(polygon.indices.toString());
                writer.write(System.getProperty("line.separator"));
            }

            writer.write(System.getProperty("line.separator"));
            writer.close();
        } catch (IOException e) {
            System.console().printf("failed to save to file");
        }

    }


    public void setUpApp() {
        setUpInvisibleEdges();
        setUpSaveButton();

        setUpViewModes();
        setUpFigures();
        setUpSliders();
    }



    private void setUpSaveButton() {
        SaveButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                saveToFile();
            }
        });

    }



    private void setUpInvisibleEdges() {
        InvisibleEdges.selectedProperty().addListener((observable, oldValue, newValue) -> {
            scene3D.drawInvisibleEdges = newValue;
            scene3D.updateViewport();
        });
    }



    private void setUpViewModes() {
        ViewMode.getItems().addAll("FRONT", "RIGHT", "TOP", "PERSPECTIVE");
        ViewMode.setValue("PERSPECTIVE");

        ViewMode.valueProperty().addListener((observable, oldValue, newValue) -> {
            scene3D.viewMode = View.valueOf(newValue.toString());
            scene3D.updateViewport();
        });
    }

    private void setUpFigures() {
        SelectedPyramid.getItems().addAll("First", "Second");
        SelectedPyramid.setValue("First");

        SelectedPyramid.valueProperty().addListener((observable, oldValue, newValue) -> {
            switch (newValue.toString()) {
                case "First": {
                    activeFigure = scene3D.firstPyramid;
                    break;
                }
                case "Second": {
                    activeFigure = scene3D.secondPyramid;
                    break;
                }
            }
        });
    }



    private void setUpSliders() {
        setUpLocation();
        setUpWorldRotation();
        setUpLocalRotation();
        setUpScale();
        setUpLocalScale();
    }

    private void setUpLocation() {
        LocationX.valueProperty().addListener((observable, oldValue, newValue) -> {
            double offset = newValue.doubleValue() - oldValue.doubleValue();
            activeFigure.translate(new double[]{offset, 0, 0, 1});
            scene3D.updateViewport();
        });

        LocationY.valueProperty().addListener((observable, oldValue, newValue) -> {
            double offset = newValue.doubleValue() - oldValue.doubleValue();
            activeFigure.translate(new double[]{0, offset, 0, 1});
            scene3D.updateViewport();
        });

        LocationZ.valueProperty().addListener((observable, oldValue, newValue) -> {
            double offset = newValue.doubleValue() - oldValue.doubleValue();
            activeFigure.translate(new double[]{0, 0, offset, 1});
            scene3D.updateViewport();
        });
    }

    private void setUpWorldRotation() {
        WorldRotationX.valueProperty().addListener((observable, oldValue, newValue) -> {
            double angle = newValue.doubleValue() - oldValue.doubleValue();
            activeFigure.rotateWorldX(angle);
            scene3D.updateViewport();
        });

        WorldRotationY.valueProperty().addListener((observable, oldValue, newValue) -> {
            double angle = newValue.doubleValue() - oldValue.doubleValue();
            activeFigure.rotateWorldY(angle);
            scene3D.updateViewport();
        });

        WorldRotationZ.valueProperty().addListener((observable, oldValue, newValue) -> {
            double angle = newValue.doubleValue() - oldValue.doubleValue();
            activeFigure.rotateWorldZ(angle);
            scene3D.updateViewport();
        });
    }

    private void setUpLocalRotation() {
        LocalRotationX.valueProperty().addListener((observable, oldValue, newValue) -> {
            double angle = newValue.doubleValue() - oldValue.doubleValue();
            activeFigure.rotateLocalX(angle);
            scene3D.updateViewport();
        });

        LocalRotationY.valueProperty().addListener((observable, oldValue, newValue) -> {
            double angle = newValue.doubleValue() - oldValue.doubleValue();
            activeFigure.rotateLocalY(angle);
            scene3D.updateViewport();
        });

        LocalRotationZ.valueProperty().addListener((observable, oldValue, newValue) -> {
            double angle = newValue.doubleValue() - oldValue.doubleValue();
            activeFigure.rotateLocalZ(angle);
            scene3D.updateViewport();
        });
    }

    private void setUpScale() {
        ScaleX.valueProperty().addListener((observable, oldValue, newValue) -> {
            double delta = newValue.doubleValue() / oldValue.doubleValue();
            activeFigure.worldScale(new double[]{delta, 1, 1, 1});
            scene3D.updateViewport();
        });

        ScaleY.valueProperty().addListener((observable, oldValue, newValue) -> {
            double delta = newValue.doubleValue() / oldValue.doubleValue();
            activeFigure.worldScale(new double[]{1, delta, 1, 1});
            scene3D.updateViewport();
        });

        ScaleZ.valueProperty().addListener((observable, oldValue, newValue) -> {
            double delta = newValue.doubleValue() / oldValue.doubleValue();
            activeFigure.worldScale(new double[]{1, 1, delta, 1});
            scene3D.updateViewport();
        });

        ScaleH.valueProperty().addListener((observable, oldValue, newValue) -> {
            double delta = newValue.doubleValue() / oldValue.doubleValue();
            activeFigure.worldScale(new double[]{1, 1, 1, delta});
            scene3D.updateViewport();
        });
    }

    private void setUpLocalScale() {
        LocalScaleX.valueProperty().addListener((observable, oldValue, newValue) -> {
            double delta = newValue.doubleValue() / oldValue.doubleValue();
            activeFigure.localScale(new double[]{delta, 1, 1, 1});
            scene3D.updateViewport();
        });

        LocalScaleY.valueProperty().addListener((observable, oldValue, newValue) -> {
            double delta = newValue.doubleValue() / oldValue.doubleValue();
            activeFigure.localScale(new double[]{1, delta, 1, 1});
            scene3D.updateViewport();
        });

        LocalScaleZ.valueProperty().addListener((observable, oldValue, newValue) -> {
            double delta = newValue.doubleValue() / oldValue.doubleValue();
            activeFigure.localScale(new double[]{1, 1, delta, 1});
            scene3D.updateViewport();
        });

        LocalScaleH.valueProperty().addListener((observable, oldValue, newValue) -> {
            double delta = newValue.doubleValue() / oldValue.doubleValue();
            activeFigure.localScale(new double[]{1, 1, 1, delta});
            scene3D.updateViewport();
        });
    }

}
