package vn.edu.tdtu.lab07.exercise03;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Switch;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.List;
import vn.edu.tdtu.lab07.R;

public class EventAdapter extends ArrayAdapter<Event> {

  private Context context;
  private int layout;
  private List<Event> data;
  private EventDatabaseHelper mDbHelper;

  public EventAdapter(@NonNull Context context, int resource, @NonNull List<Event> objects,
      EventDatabaseHelper mDbHelper) {
    super(context, resource, objects);
    this.context = context;
    this.layout = resource;
    this.data = objects;
    this.mDbHelper = mDbHelper;
  }

  @NonNull
  @Override
  public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

    ViewHolder holder;
    if (convertView == null) {
      convertView = LayoutInflater.from(context).inflate(layout, parent, false);

      holder = new ViewHolder();
      holder.name = convertView.findViewById(R.id.tv_name);
      holder.place = convertView.findViewById(R.id.tv_place);
      holder.datetime = convertView.findViewById(R.id.tv_datetime);
      holder.eventSwitch = convertView.findViewById(R.id.sw_switch);

      convertView.setTag(holder);

    } else {
      holder = (ViewHolder) convertView.getTag();
    }

    final Event event = data.get(position);

    holder.name.setText(event.getName());
    holder.place.setText(event.getPlace());
    holder.datetime.setText(event.getDate() + " " + event.getTime());

    holder.eventSwitch.setChecked(event.getCompleted());
    holder.eventSwitch.setTag(position); // attach position to checkbox

    holder.eventSwitch.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        boolean checked = event.getCompleted();
        event.setCompleted(!checked);
        mDbHelper.updateEvent(event);
        notifyDataSetChanged();
      }
    });
    return convertView;
  }

  public void setEvents(List<Event> events) {
    this.data = events;
    notifyDataSetChanged();
  }

  public static class ViewHolder {

    TextView name;
    TextView place;
    TextView datetime;
    Switch eventSwitch;
  }
}