import java.rmi.RemoteException;

import org.apache.commons.math3.stat.regression.ModelSpecificationException;
import org.apache.commons.math3.stat.regression.SimpleRegression;

public class RegressionFunction 
{

    public static void main(String[] args) throws ModelSpecificationException, RemoteException 
    {

        // creating regression object, passing true to have intercept term
        SimpleRegression simpleRegression = new SimpleRegression(true);

        // passing data to the model
        // model will be fitted automatically by the class 
        simpleRegression.addData(getInfo.getRecords());

        // querying for model parameters
        System.out.println("slope = " + simpleRegression.getSlope());
        System.out.println("intercept = " + simpleRegression.getIntercept());

        // trying to run model for unknown data
        System.out.println("prediction for 101 = " + simpleRegression.predict(101));

    }

}