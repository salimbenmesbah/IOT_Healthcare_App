package réseauxBayésiens;
import java.util.LinkedList;

import smile.Network;
//import FuzzyKnowledge.ObservedState;
//import FuzzyKnowledge.ObservedValue;

public class InferenceFloue {
    //FuzzyBayesianNetwork fbn;
	//LinkedList<ObservedValue> fuzzyevidence;
	LinkedList<String> crispevidece;

	/*public FuzzyInference(	FuzzyBayesianNetwork fbn){
		this.fbn=fbn;

	} */


    public static void main (String args[]){
        new smile.License(
                "SMILE LICENSE d41161b6 8b72612c ed417b5a " +
                        "THIS IS AN ACADEMIC LICENSE AND CAN BE USED " +
                        "SOLELY FOR ACADEMIC RESEARCH AND TEACHING, " +
                        "AS DEFINED IN THE BAYESFUSION ACADEMIC " +
                        "SOFTWARE LICENSING AGREEMENT. " +
                        "Serial #: en05exaj9ztpxsp2znslb6es6 " +
                        "Issued for: ta meree (laughoutloudly417@gmail.com) " +
                        "Academic institution: Saad dahleb Blida1 " +
                        "Valid until: 2022-11-24 " +
                        "Issued by BayesFusion activation server",
                new byte[] {
                        -116,120,-3,100,57,-127,27,-41,10,19,4,-86,-88,-126,-15,86,
                        -47,59,-93,-127,-3,-101,-56,-63,-100,75,11,-32,86,107,-91,53,
                        -30,82,42,-14,-34,-118,-103,-79,22,-128,-51,1,96,53,119,38,
                        80,54,58,-1,107,-47,-26,42,49,64,100,38,-115,-121,111,27
                }
        );

        System.loadLibrary("jsmile");
        Network net = new Network();
        // load the network created by Tutorial1
        net.readFile("C:\\Users\\USER\\IdeaProjects\\PFE_Master\\src\\réseauxBayésiens\\RéseauBayesienClassique2.xdsl");
        System.out.println("Posteriors with no evidence set:");
        //net.updateBeliefs();
        //printPosteriors(net, 0);

        /* ObservedState ob=new ObservedState("Sex", "Male");
        LinkedList<ObservedState> crispevidence = new LinkedList<ObservedState>();
        crispevidence.add(ob); */

        //crispInference(net,crispevidence);
        //printPosteriors(net,0);

    }
    public static void printPosteriors(Network net, int nodeHandle) {
        String nodeId = net.getNodeId(nodeHandle);

        double[] posteriors = net.getNodeValue(nodeHandle);

        for (int i = 0; i < posteriors.length; i ++) {
            System.out.printf("P(%s=%s)=%f\n", nodeId,	net.getOutcomeId(nodeHandle, i),
                    posteriors[i]);
        }
    }



    /// predict disease


    /* private static void crispInference(Network net, LinkedList<ObservedState> crispevidence) {
        double[] ver={0.3,0.7};
        String handle;
        String state;
        for(ObservedState obstate :crispevidence){
            handle=obstate.getNode();
            state=obstate.getState();

            if (state != null) {
                //net.setEvidence(handle, state);
                net.setVirtualEvidence(handle,ver);

            } else {
                net.clearEvidence(handle);
            }
            net.updateBeliefs();
        }

    } */
	/*public static void fuzzyInference(Network net, LinkedList<ObservedValue> fuzzyevidence){

		for(ObservedValue obstate :fuzzyevidence){
			handle=obstate.getNode();
			 state=obstate.getState();

				if (state != null) {
					//net.setEvidence(handle, state);
					net.setVirtualEvidence(handle,ver);

					} else {
					net.clearEvidence(handle);
					}
				net.updateBeliefs();
		}

	}
	*/

}
