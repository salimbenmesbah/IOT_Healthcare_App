package réseauxBayésiens;
import com.fuzzylite.Engine;
import com.fuzzylite.FuzzyLite;
import com.fuzzylite.Op;

import com.fuzzylite.term.Term;
import com.fuzzylite.term.Trapezoid;
import com.fuzzylite.term.Triangle;
import com.fuzzylite.variable.InputVariable;

import java.util.ArrayList;
import java.util.List;
public class Fuzzification {
    Engine engine;


    public Fuzzification(){
        this.engine = new Engine();
        engine.setName("FuzzyBayes");
    }

    // a and b are the limits of the range of the values that the fuzzy node can take
    public InputVariable createFuzzyNode(String name,double a, double b){
        InputVariable e= new InputVariable (name);
        e.setRange(a, b);
        return e;
    }


    public void addFuzzyState(InputVariable e, Term t ){
        e.addTerm(t);

    }


    public double getMembershipDegree(InputVariable e, double a, String t){
        e.setInputValue(a);
        engine.process();
        return e.getTerm(t).membership(a);
    }

    public double normalize(double val, double sum){
        return val/sum;
    }
}
