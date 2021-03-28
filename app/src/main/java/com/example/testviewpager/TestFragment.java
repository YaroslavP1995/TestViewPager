package com.example.testviewpager;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class TestFragment extends Fragment {
    private Unbinder unbinder;
    private IncrementFragmentClickListener incrementFragmentClickListener;
    private int currentItem;
    private Activity activity;
    private NotificationManager notificationManager;

    @BindView(R.id.textIncrement)
    TextView textView;
    @BindView(R.id.remove_fragment)
    CardView removeFragment;

    public static TestFragment getInstance(IncrementFragmentClickListener clickListener, int currentItem, Activity activity) {
        TestFragment fragment = new TestFragment();
        fragment.incrementFragmentClickListener = clickListener;
        fragment.currentItem = currentItem;
        fragment.activity = activity;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout, container, false);
        unbinder = ButterKnife.bind(this, view);
        if (currentItem == 0) {
            removeFragment.setVisibility(View.GONE);
        } else {
            removeFragment.setVisibility(View.VISIBLE);
        }
        textView.setText(currentItem + "");
        return view;
    }

    @OnClick({R.id.add_fragment, R.id.remove_fragment, R.id.add_notification})
    public void createCommPost(View view) {
        switch (view.getId()) {
            case R.id.add_fragment:
                incrementFragmentClickListener.incrementClickListener(true);
                break;
            case R.id.remove_fragment:
                incrementFragmentClickListener.incrementClickListener(false);
                removeNotification(currentItem);
                break;
            case R.id.add_notification:
                sendNotification(currentItem);
                break;
        }
    }


    private void sendNotification(int index){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(activity,"id");
        builder.setAutoCancel(true);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("Messenger");
        builder.setContentTitle("Chat heads active");
        builder.setContentText("Notification "+index);
        Notification notification = builder.build();

         notificationManager =
                (NotificationManager) activity.getSystemService(activity.NOTIFICATION_SERVICE);
        notificationManager.notify(index, notification);

    }

    private void removeNotification(int index){
         notificationManager =
                (NotificationManager) activity.getSystemService(activity.NOTIFICATION_SERVICE);
        notificationManager.cancel(index);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
