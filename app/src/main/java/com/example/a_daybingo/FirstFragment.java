package com.example.a_daybingo;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a_daybingo.databinding.FragmentFirstBinding;

import java.util.ArrayList;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /*
        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });*/


        BoardAdapter boardAdapter = new BoardAdapter(MainActivity.getBingoItems());
        binding.boardView.setItemAnimator(new DefaultItemAnimator());
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 4);
        binding.boardView.setLayoutManager(layoutManager);
        binding.boardView.setAdapter(boardAdapter);


        binding.tally1.setText(String.valueOf(MainActivity.tallyItems.get(0).getTimesTallied()));
        binding.tally2.setText(String.valueOf(MainActivity.tallyItems.get(1).getTimesTallied()));

        binding.displayTallyCardView1.setBackgroundResource(R.drawable.card_view_background);
        binding.displayTallyCardView2.setBackgroundResource(R.drawable.card_view_background);

        binding.minusButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.tallyItems.get(0).minusTimesTallied();
                binding.tally1.setText(String.valueOf(MainActivity.tallyItems.get(0).getTimesTallied()));
            }
        });

        binding.minusButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.tallyItems.get(1).minusTimesTallied();
                binding.tally2.setText(String.valueOf(MainActivity.tallyItems.get(1).getTimesTallied()));
            }
        });

        binding.plusButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.tallyItems.get(0).addTimesTallied();
                binding.tally1.setText(String.valueOf(MainActivity.tallyItems.get(0).getTimesTallied()));
            }
        });

        binding.plusButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.tallyItems.get(1).addTimesTallied();
                binding.tally2.setText(String.valueOf(MainActivity.tallyItems.get(1).getTimesTallied()));
            }
        });



    }

    public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.ViewHolder>{

        private ArrayList<BingoItem> list;

        public class ViewHolder extends RecyclerView.ViewHolder{
            public TextView bingoTaskDisplay;
            public ImageView xImage;
            public CardView cardDisplayView;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                bingoTaskDisplay = itemView.findViewById(R.id.bingoTaskDisplay);
                xImage = itemView.findViewById(R.id.xImage);
                cardDisplayView = itemView.findViewById(R.id.cardDisplayView);

                itemView.setOnClickListener(this::onClick);
            }

            public void onClick(View view) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    BingoItem item = list.get(position);
                    item.setChecked();
                    whenClicked(item, xImage);
                }
            }
        }

        public BoardAdapter(ArrayList<BingoItem> list){
            this.list = list;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);
            View hourlyView = inflater.inflate(R.layout.bingo_piece_layout, parent, false);
            ViewHolder viewHolder = new ViewHolder(hourlyView);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            BingoItem item = list.get(position);

            holder.cardDisplayView.setBackgroundResource(R.drawable.card_view_background);
            holder.bingoTaskDisplay.setText(item.getTask());
            whenClicked(item, holder.xImage);
            holder.xImage.setImageResource(R.drawable.x_image);
        }

        @Override
        public int getItemCount() {
            return list.size();
        }


    }

    public void whenClicked(BingoItem item, ImageView image){
        if (item.isChecked())
            image.setVisibility(View.VISIBLE);
        else
            image.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}