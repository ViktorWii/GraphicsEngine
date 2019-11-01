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

    public Slider LocalScaleX;
    public Slider LocalScaleY;
    public Slider LocalScaleZ;
    public Slider LocalScaleH;


    public ChoiceBox ViewMode;
    public ChoiceBox SelectedPyramid;


    public Figure activeFigure;

    public Scene3D scene3D;


    public void setUpApp() {
        setUpViewModes();
        setUpFigures();
        setUpSliders();
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
