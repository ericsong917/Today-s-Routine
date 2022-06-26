package edu.skku.cs.todayroutine;

import android.media.AudioManager;
import android.media.ToneGenerator;

import java.util.Timer;
import java.util.TimerTask;

public class GoModel implements GoContract.ContractForModel{
    static private int minute;
    static private int second;
    static private int remaining_units;
    public GoModel(int min,int sec,int u){
        this.minute=min;
        this.second=sec;
        this.remaining_units=u;
    }

    @Override
    public int getRemainSets() {
        return this.remaining_units;
    }

    @Override
    public int getMinute() {
        return this.minute;
    }

    @Override
    public int getSecond() {
        return this.second;
    }

    @Override
    public void setRemainSets(OnValueChangedListener listener) {
        this.remaining_units--;
        listener.onChanged();
    }

    @Override
    public void StartTimer(OnValueChangedListener listener) {
        int initial_minute=GoModel.minute;
        int initial_second=GoModel.second;
        Timer timer= new Timer();
        TimerTask timerTask=new TimerTask() {
            @Override
            public void run() {
                if( GoModel.second!=0){
                    GoModel.second--;
                    listener.onChanged_time();
                }
                else if(GoModel.minute !=0){
                    GoModel.second=60;
                    GoModel.second--;
                    GoModel.minute--;
                    listener.onChanged_time();;
                }
                if(GoModel.minute==0 && GoModel.second==0){
                    GoModel.remaining_units--;
                    GoModel.second=initial_second;
                    GoModel.minute=initial_minute;
                    listener.onChanged();
                    ToneGenerator toneGen1=new ToneGenerator(AudioManager.STREAM_MUSIC,100);
                    if(GoModel.remaining_units==0){
                        toneGen1.startTone(ToneGenerator.TONE_PROP_BEEP2,300);

                    }
                    else{
                        toneGen1.startTone(ToneGenerator.TONE_CDMA_PIP,150);
                    }


                    timer.cancel();
                }
            }
        };
        timer.schedule(timerTask,0,1000);

    }
}
