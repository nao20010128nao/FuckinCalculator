package com.nao20010128nao.FuckinCalculator

import android.os.Bundle
import android.os.Handler
import android.support.design.widget.Snackbar
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.beardedhen.androidbootstrap.BootstrapButton

public class NCRFragment extends BaseFragment {
    TextView n, r,answer
    BootstrapButton calc
    Handler handler=new Handler()
    @Override
    public void onResume() {
        super.onResume()
        n=findViewById(R.id.n) as TextView
        r=findViewById(R.id.r) as TextView
        answer=findViewById(R.id.answer) as TextView
        calc=findViewById(R.id.calculate) as BootstrapButton
        calc.onClickListener={
            def wasError=0
            if(TextUtils.isEmpty(n.text)||!n.text.matches(Utils.PATTERN_NUMBER_ANY_DIGIT)){
                wasError++
                n.error=resources.getString(R.string.invalid_value)
            }
            if(TextUtils.isEmpty(r.text)||!r.text.matches(Utils.PATTERN_NUMBER_ANY_DIGIT)){
                wasError++
                r.error=resources.getString(R.string.invalid_value)
            }
            if(wasError!=0){
                Snackbar.make(n,String.format(resources.getQuantityText(R.plurals.was_error,wasError).toString(),wasError),Snackbar.LENGTH_LONG).show()
                return
            }
            new Thread({
                try{
                    BigInteger value=Utils.nCr(n.text.toBigInteger(),r.text.toBigInteger())
                    handler.post({
                        answer.text=value.toString()
                    })
                }catch(Throwable e){
                    Log.e("Error","",e)
                    handler.post({
                        Snackbar.make(n,"${e.class.name}: ${e.message}",Snackbar.LENGTH_LONG).show()
                    })
                }
            }).start()
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ncr, container, false)
    }
}
