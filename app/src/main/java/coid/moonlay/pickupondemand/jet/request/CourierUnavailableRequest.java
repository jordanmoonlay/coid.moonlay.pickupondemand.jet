package coid.moonlay.pickupondemand.jet.request;

import android.content.Context;

import coid.moonlay.pickupondemand.jet.R;
import coid.moonlay.pickupondemand.jet.Utility;
import coid.moonlay.pickupondemand.jet.activity.MainActivity;
import coid.moonlay.pickupondemand.jet.base.BaseNetworkRequest;
import coid.moonlay.pickupondemand.jet.model.CourierAvailability;
import coid.moonlay.pickupondemand.jet.model.UserProfile;
import coid.moonlay.pickupondemand.jet.network.RetrofitProvider;
import retrofit2.Call;
import retrofit2.Response;

public class CourierUnavailableRequest extends BaseNetworkRequest<CourierAvailability>
{
    public CourierUnavailableRequest(Context context)
    {
        super(context);
    }

    @Override
    protected Call<CourierAvailability> getCall()
    {
        return RetrofitProvider.getAuthorizedResourcesService().setCourierUnavailable();
    }

    @Override
    protected void onSuccessOnUIThread(Response<CourierAvailability> response)
    {
        super.onSuccessOnUIThread(response);
        showToast(Utility.Message.get(R.string.dashboard_offline_message));
    }

    @Override
    protected void onResponseFailedOnNetworkThread(Response<CourierAvailability> response)
    {
        super.onResponseFailedOnNetworkThread(response);
        showToast(Utility.Message.getResponseMessage(R.string.failed_to_go_offline, response));
    }

    @Override
    protected void onFailedOnNetworkThread(Exception ex)
    {
        super.onFailedOnNetworkThread(ex);
        showToast(Utility.Message.getNetworkFailureMessage(R.string.failed_to_go_offline, ex));
    }

    @Override
    protected void onTimeOutOnNetworkThread()
    {
        super.onTimeOutOnNetworkThread();
        showToast(Utility.Message.get(R.string.request_timed_out));
    }
}
