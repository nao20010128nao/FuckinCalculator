package com.nao20010128nao.FuckinCalculator

import android.os.Bundle
import android.os.Handler
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.text.SpannableStringBuilder
import android.text.TextUtils
import android.text.style.RelativeSizeSpan
import android.text.style.SubscriptSpan
import android.text.style.SuperscriptSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.beardedhen.androidbootstrap.BootstrapButton
import kotlin.sequences.SubSequence

public class MultiplyCoefficientsFragment extends BaseFragment {
    TextView n, answer
    BootstrapButton calc
    Handler handler=new Handler()
    @Override
    public void onResume() {
        super.onResume()
        n=findViewById(R.id.n) as TextView
        answer=findViewById(R.id.answer) as TextView
        calc=findViewById(R.id.calculate) as BootstrapButton
        calc.onClickListener={
            def wasError=0
            if(TextUtils.isEmpty(n.text)||!n.text.matches(Utils.PATTERN_NUMBER_ANY_DIGIT)){
                wasError++
                n.error=resources.getString(R.string.invalid_value)
            }
            if(wasError!=0){
                Snackbar.make(n,String.format(resources.getQuantityText(R.plurals.was_error,wasError).toString(),wasError),Snackbar.LENGTH_LONG).show()
                return
            }
            new Thread({
                try{
                    BigInteger givenN=n.text.toBigInteger()
                    List<BigInteger[]> value=Utils.multiplyCoefficients(givenN)
                    SpannableStringBuilder ssb=new SpannableStringBuilder()
                    SpannableStringBuilder result=new SpannableStringBuilder()
                    StringBuilder resultCopy=new StringBuilder()
                    result.append(resources.getString(R.string.result_equals))
                    resultCopy.append(resources.getString(R.string.result_equals_for_copy_paste))
                    for(BigInteger[] bi:value){
                        /*
                        * r = it[0] (x^it[1]y^it[2])
                        * [n field]Cr = it[3]
                        * //[n field]Cr * [xFactor] * [yFactor] = it[4]
                        * ...
                        *
                        * */
                        Log.d("it.class","${bi.class}")
                        ssb.append("r = ${bi[0]} (x").append(Utils.applySpannable("${bi[1]}",new SuperscriptSpan())).append("y").append(Utils.applySpannable("${bi[2]}",new SuperscriptSpan())).append(")\n")
                        ssb.append(Utils.applySpannable("${givenN}",new RelativeSizeSpan(0.8f))).append("Cr = ${bi[3]}\n")
                        //ssb.append(Utils.applySpannable("${n}",new SubscriptSpan())).append("Cr * ${1} * ${1} = ${it[4]}\\n")
                        ssb.append("\n"*2)

                        result.append("${bi[4]}x").append(Utils.applySpannable("${bi[1]}",new SuperscriptSpan())).append("y").append(Utils.applySpannable("${bi[2]}",new SuperscriptSpan())).append("+")
                        resultCopy.append("${bi[3]}*x^${bi[1]}*y^${bi[2]}+")
                    }
                    ssb.append("\n").append(result,0,result.length()-1).append("\n").append(resultCopy,0,resultCopy.length()-1)
                    handler.post({
                        answer.text=ssb
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
        return inflater.inflate(R.layout.fragment_multiply_coefficients, container, false)
    }
}
