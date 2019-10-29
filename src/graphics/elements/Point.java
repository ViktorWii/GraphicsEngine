package graphics.elements;

public class Point {
    // Z position of Camera
    private double z0 = 100;


    public double x;
    public double y;
    public double z;
    public double h = 1;

    public double local_x;
    public double local_y;
    public double local_z;

    public Point(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;

        local_x = x;
        local_y = y;
        local_z = z;
    }

    public double[] getWorldLocation() {
        return new double[]{x, y, z, h};
    }

    public double[] getLocalLocation() {
        return new double[]{x, y, z, h};
    }

    public void setWorldLocation(double[] position) {
        x = position[0];
        y = position[1];
        z = position[2];
    }

    public void setLocalLocation(double[] position) {
        local_x = position[0];
        local_y = position[1];
        local_z = position[2];

        setWorldLocation(new double[]{x + local_x, y + local_y, z + local_z});
    }


    public double[] getProjection(View viewMode) {
        switch (viewMode) {
            case TOP: {
                return new double[]{x, y};
            }
            case FRONT: {
                return new double[]{y, z};
            }
            case RIGHT: {
                return new double[]{x, z};
            }
            case ONE_POINT_PERSPECTIVE: {
                double[] projection = applyOperation(getOperationOnePointPerspectiveProject());
                return new double[]{projection[0] / projection[3], projection[1] / projection[3]};
            }

        }

        return null;
    }

    public void translate(double[] translation) {
        double[] newLocation = applyOperation(getOperationTranslate(translation));
        setWorldLocation(newLocation);
    }

    public void rotateX(double angle) {
        double radians = angle * Math.PI / 180;
        double[] newLocation = applyOperation(getOperationRotateX(radians));
        setWorldLocation(newLocation);
    }

    public void rotateY(double angle) {

        double radians = angle * Math.PI / 180;
        double[] newLocation = applyOperation(getOperationRotateY(radians));
        setWorldLocation(newLocation);
    }

    public void rotateZ(double angle) {
        double radians = angle * Math.PI / 180;
        double[] newLocation = applyOperation(getOperationRotateZ(radians));
        setWorldLocation(newLocation);
    }

    public void scale(double[] scale) {
        double[] newLocation = applyOperation(getOperationScale(scale), false);
        setLocalLocation(newLocation);
    }



    private double[] applyOperation(double[][] operation) {
        return applyOperation(operation, true);
    }

    private double[] applyOperation(double[][] operation, boolean isWorld) {
        double[] location = isWorld ? getWorldLocation() : getLocalLocation();

        double[] result = new double[4];
        for (int axis = 0; axis < result.length; axis++) {
            for (int i = 0; i < location.length; i++)
                result[axis] += location[i] * operation[i][axis];
        }

        return result;
    }



    private double[][] getOperationOnePointPerspectiveProject() {
        return new double[][]{
                {1, 0, 0, 0},
                {0, 1, 0, 0},
                {0, 0, 0, -1 / z0},
                {0, 0, 0, 1}
        };
    }

    private double[][] getOperationRotateX(double angle) {
        return new double[][]{
                {1, 0, 0, 0},
                {0, Math.cos(angle), Math.sin(angle), 0},
                {0, -Math.sin(angle), Math.cos(angle), 0},
                {0, 0, 0, 1}
        };
    }

    private double[][] getOperationRotateY(double angle) {
        return new double[][]{
                {Math.cos(angle), 0, -Math.sin(angle), 0},
                {0, 1, 0, 0},
                {Math.sin(angle), 0, Math.cos(angle), 0},
                {0, 0, 0, 1}
        };
    }

    private double[][] getOperationRotateZ(double angle) {
        return new double[][]{
                {Math.cos(angle), Math.sin(angle), 0, 0},
                {-Math.sin(angle), Math.cos(angle), 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1}
        };
    }

    private double[][] getOperationTranslate(double[] translation) {
        return new double[][]{
                {1, 0, 0, 0},
                {0, 1, 0, 1},
                {0, 0, 1, 0},
                {translation[0], translation[1], translation[2], 1}
        };
    }

    private double[][] getOperationScale(double[] scale) {
        return new double[][]{
                {scale[0], 0, 0, 0},
                {0, scale[1], 0, 0},
                {0, 0, scale[2], 0},
                {0, 0, 0, scale[3]}
        };
    }

}
