package business;

import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;

@Stateless
public class MyTimerService {

	@Resource
	TimerService timerService;
	
	private static final Logger logger = Logger.getLogger("business.MyTimerService");
	
	public MyTimerService() {
		
	}
	
	public void setTimer(long interval) {
		timerService.createTimer(interval, "Setting a programmatic timer");
	}
	
	@Timeout
	public void ProgrammaticTimout(Timer timer) {
		logger.info("@Timeout in programmatic timer at " + new java.util.Date());
	}
}
