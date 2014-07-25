package actions;

import play.Logger;
import play.libs.F.Promise;
import play.mvc.Action;
import play.mvc.Http.Context;
import play.mvc.Result;

public class TestAction extends Action<TestAction> {

	@Override
	public Promise<Result> call(Context ctx) throws Throwable {
		Logger.debug("Calling TestAction");
		return delegate.call(ctx);
	}

}
