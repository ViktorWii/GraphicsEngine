package graphics.engine;

import graphics.elements.Figure;
import graphics.elements.View;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;

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

    public ChoiceBox ViewMode;



    public Figure activeFigure;

    public Scene3D scene3D;


    public void setUpApp() {
        setUpViewModes();
        setUpSliders();
    }

    private void setUpViewModes() {
        ViewMode.getItems().addAll("FRONT", "RIGHT", "TOP", "ONE_POINT_PERSPECTIVE");

        ViewMode.valueProperty().addListener((observable, oldValue, newValue) -> {
            scene3D.viewMode = View.valueOf(newValue.toString());
            scene3D.updateViewport();
        });
    }

    private void setUpSliders() {
        setUpLocation();
        setUpWorldRotation();
        setUpScale();
    }

    private void setUpLocation() {
        LocationX.valueProperty().addListener((observable, oldValue, newValue) -> {
            double offset = newValue.doubleValue() - oldValue.doubleValue();
            activeFigure.translate(new double[]{offset, 0, 0});
            scene3D.updateViewport();
        });

        LocationY.valueProperty().addListener((observable, oldValue, newValue) -> {
            double offset = newValue.doubleValue() - oldValue.doubleValue();
            activeFigure.translate(new double[]{0, offset, 0});
            scene3D.updateViewport();
        });

        LocationZ.valueProperty().addListener((observable, oldValue, newValue) -> {
            double offset = newValue.doubleValue() - oldValue.doubleValue();
            activeFigure.translate(new double[]{0, 0, offset});
            scene3D.updateViewport();
        });
    }

    private void setUpWorldRotation() {
        WorldRotationX.valueProperty().addListener((observable, oldValue, newValue) -> {
            double angle = newValue.doubleValue() - oldValue.doubleValue();
            activeFigure.rotateX(angle);
            scene3D.updateViewport();
        });

        WorldRotationY.valueProperty().addListener((observable, oldValue, newValue) -> {
            double angle = newValue.doubleValue() - oldValue.doubleValue();
            activeFigure.rotateY(angle);
            scene3D.updateViewport();
        });

        WorldRotationZ.valueProperty().addListener((observable, oldValue, newValue) -> {
            double angle = newValue.doubleValue() - oldValue.doubleValue();
            activeFigure.rotateZ(angle);
            scene3D.updateViewport();
        });
    }

    private void setUpScale() {
        ScaleX.valueProperty().addListener((observable, oldValue, newValue) -> {
            double delta = newValue.doubleValue() / oldValue.doubleValue();
            activeFigure.scale(new double[]{delta, 1, 1, 1});
            scene3D.updateViewport();
        });

        ScaleY.valueProperty().addListener((observable, oldValue, newValue) -> {
            double delta = newValue.doubleValue() / oldValue.doubleValue();
            activeFigure.scale(new double[]{1, delta, 1, 1});
            scene3D.updateViewport();
        });

        ScaleZ.valueProperty().addListener((observable, oldValue, newValue) -> {
            double delta = newValue.doubleValue() / oldValue.doubleValue();
            activeFigure.scale(new double[]{1, 1, delta, 1});
            scene3D.updateViewport();
        });

        ScaleH.valueProperty().addListener((observable, oldValue, newValue) -> {
            double delta = newValue.doubleValue() / oldValue.doubleValue();
            activeFigure.scale(new double[]{1, 1, 1, delta});
            scene3D.updateViewport();
        });
    }
}
