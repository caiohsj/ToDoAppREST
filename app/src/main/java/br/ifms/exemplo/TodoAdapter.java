package br.ifms.exemplo;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class TodoAdapter extends BaseAdapter {

    private List<Todo> todos;
    private AppCompatActivity activity;

    public TodoAdapter(AppCompatActivity activity, List<Todo> todos){
        this.activity = activity;
        this.todos = todos;
    }

    @Override
    public int getCount() {
        return todos.size();
    }

    @Override
    public Object getItem(int position) {
        return todos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return todos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = activity.getLayoutInflater().inflate(R.layout.listview_adapter, parent, false);
        TextView campoDescricao = view.findViewById(R.id.textview_listview_adapter_descricao);
        TextView campoPrioridade =view.findViewById(R.id.textview_listview_adapter_prioridade);

        Todo todo = todos.get(position);

        campoDescricao.setText(todo.getDescricao());
        campoPrioridade.setText(todo.getPrioridade());

        return view;
    }
}
