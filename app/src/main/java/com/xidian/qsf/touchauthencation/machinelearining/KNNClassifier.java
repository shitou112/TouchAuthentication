package com.xidian.qsf.touchauthencation.machinelearining;

import com.xidian.qsf.touchauthencation.FeatureVector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




public class KNNClassifier extends Classifier {


	@SuppressWarnings("serial")
	public class KNNModel implements java.io.Serializable {
		
		public List<FeatureVector> data;
		
		public int k;
		
		
		public int numFeatures;
		
		public KNNModel(int k, int numFeatures){
			this.k = k;
			this.numFeatures = numFeatures;
		}
		
		
		public KNNModel(Object objModel){
			KNNModel model = (KNNModel) objModel;
			this.k = model.k;
			this.numFeatures = model.numFeatures;
			this.data = model.data;
		}
	}
	
	
	public static class ComputedDistance {
		
		int label;
		
		
		double distance;
		
		public ComputedDistance(int label, double distance) {
			this.label = label;
			this.distance = distance;
		}
	}
	public KNNModel model;
	
	
	public KNNClassifier(int k, int numFeatures) 
			throws IllegalArgumentException {
		if(k < 1 || numFeatures < 1)
			throw new IllegalArgumentException("Both 'k' and 'numFeatures' "+
					"must be greater than or equal to 1");
		model = new KNNModel(k, numFeatures);

	}
	
	
	public KNNClassifier(Object objModel) {
		model = new KNNModel(objModel);

	}
	
	
	@Override
	public boolean train(List<FeatureVector> data)
			throws IllegalArgumentException{
		if (data == null || data.size() < 1) 
			throw new IllegalArgumentException("Provided data is invalid");
		model.data = data;
		return true;
	}

	@Override
	public int classify(FeatureVector featureVector) 
			throws IllegalStateException{
		/*Sanity checking*/


		if (featureVector.size() != model.data.get(0).size()) {
			System.out.println("FeatureVector size mismatch in Classify");
			return 0;
		}
		List<ComputedDistance> computedDistance = 
				new ArrayList<ComputedDistance>();
		
		
		for (FeatureVector fv : model.data) {
			double eucDistance = 0;
			for (int i = 0; i < model.numFeatures; i++)
				eucDistance += Math.pow(fv.get(i)- featureVector.get(i), 2);
			eucDistance = Math.sqrt(eucDistance);
			computedDistance.add(new ComputedDistance(fv.getClassLabel(),
					eucDistance));
		}
		
		List<ComputedDistance> sortedDistance = sortDistances(computedDistance);
		return getMajorityLabel(sortedDistance);
	}
	

	public Object getModel() {
		return this.model;
	}
	
	
	public ArrayList<ComputedDistance> sortDistances(List<ComputedDistance> 
	computedDistance) {
		ArrayList<ComputedDistance> sortedDistance = 
				new ArrayList<ComputedDistance>();
		for (int i = 0; i < model.k; i++) {
			double minDistance = Double.MAX_VALUE;
			int minIndex = -1;
			for (int j = 0; j < computedDistance.size(); j++)
				if (minDistance > computedDistance.get(j).distance) {
					minIndex = j;
					minDistance = computedDistance.get(j).distance;
				}
			if (minIndex == -1)
				break;
			sortedDistance.add(new ComputedDistance(
					computedDistance.get(minIndex).label, 
					computedDistance.get(minIndex).distance));
			computedDistance.remove(minIndex);
		}
		return sortedDistance;
	}
	
	public int getMajorityLabel(List<ComputedDistance> sortedDistance ) {
		if (sortedDistance.size() == 0 )
			return 0;
		Map <Integer, Integer> majoritySum = new HashMap<Integer, Integer>();
		for (ComputedDistance cd : sortedDistance) {
			if (majoritySum.containsKey(cd.label))
				majoritySum.put(cd.label, majoritySum.get(cd.label)+1);
			else
				majoritySum.put(cd.label, 1);
		}
		int majorityClassLabel = -1;
		int maxOccurences = -1;
		for (int classLabel : majoritySum.keySet()) {
			if (majoritySum.get(classLabel) > maxOccurences) {//XXX: break ties
				majorityClassLabel = classLabel;
				maxOccurences = majoritySum.get(classLabel);
			}
		}
		return majorityClassLabel;
	}
	
	public int getK() {
		return model.k;
	}

	
	public void setK(int k) throws IllegalArgumentException {
		if (k < 1)
			throw new IllegalArgumentException("k must be greater than zero");
		model.k = k;
	}

	
	public int getNumFeatures() {
		return model.numFeatures;
	}

	
	public boolean setNumFeatures(int numFeatures) 
			throws IllegalArgumentException {
		if (model.data.size() > 0)
			if (model.data.get(0).size() <= numFeatures)
				throw new IllegalArgumentException("restriction: "
						+ "numFeatures > 0 and existing dataset should "
						+ " contain <= numFeatures features"); 
		model.numFeatures = numFeatures;
		return true;
	}
	
	
}
