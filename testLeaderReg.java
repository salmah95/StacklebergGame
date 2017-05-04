import comp34120.ex2.PlayerImpl;
import comp34120.ex2.PlayerType;
import comp34120.ex2.Record;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import org.apache.commons.math3.stat.regression.SimpleRegression;


final class testLeaderReg
	extends PlayerImpl
{
	//this makes a new regression function so we can use it 
    SimpleRegression simpleRegression = new SimpleRegression(true);
    //an array to store information specifically about the prices, just in case we 
    //can reuse this
    private final static float[][] info = new float [101][3];
    //an array that stores all the information about the records 
    private final static Record [] [] records = new Record [101][3];
    
    private testLeaderReg()
    		throws RemoteException, NotBoundException
    	{
    		super(PlayerType.LEADER, "Test Leader");
    	}

    	@Override
    	public void goodbye()
    		throws RemoteException
    	{
    		ExitTask.exit(500);
    	}
    	
    	public void proceedNewDay(int p_date)
    			throws RemoteException
    		{
    			
    	               for(int x= 1; x <= 100; x++)
    		    {
    			  
    			  records [x][1] = m_platformStub.query(PlayerType.LEADER, x);
    			  records [x][2] = m_platformStub.query(PlayerType.FOLLOWER, x);
    	                  info[x][1] = records[x][1].m_leaderPrice; 
    			  info[x][2] = records[x][2].m_followerPrice; 
    	                  m_platformStub.log(m_type, "Our price " + Float.toString(info[x][1]) 
    	                                      + ", their Price " + Float.toString(info[x][2]));
    	                  simpleRegression.addData(info[x][1], info[x][2]);
    			  
    			}

    	               m_platformStub.log(m_type, "slope = " + simpleRegression.getSlope());
    	               m_platformStub.log(m_type, "intercept = " + simpleRegression.getIntercept());
    	                m_platformStub.log(m_type, "prediction for 101 = " + simpleRegression.predict(101));  
    	               
    	               
    			
    		}
    	
    	
    	public static void main(final String[] p_args)
    			throws RemoteException, NotBoundException
    		{
    			new testLeaderReg();
    		}
    	        @Override
    		public void endSimulation()
    			throws RemoteException
    		{
    			m_platformStub.log(m_type, "hey");
    		}

    	
    		private static class ExitTask
    			extends TimerTask
    		{
    			static void exit(final long p_delay)
    			{
    				(new Timer()).schedule(new ExitTask(), p_delay);
    			}
    			
    			@Override
    			public void run()
    			{
    				System.exit(0);
    			}
    		}

  
}