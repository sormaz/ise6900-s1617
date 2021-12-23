package edu.ohio.ise.ise6900.test;

import java.rmi.RemoteException;

import nxopen.NXException;
import nxopen.SessionFactory;
import nxopen.Tag;
import nxopen.UFSession;
import nxopen.uf.UFModeling;
import nxopen.uf.UFPart;
import nxopen.uf.UFPart.OpenData;

public class NX1899Tester {
	static UFSession theUfSession =null;
	static int success = -1;
	static int partTag = -1;
	
	public NX1899Tester() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		String partFile = args[0];
		try {
			theUfSession = (UFSession)SessionFactory.get("UFSession");
			if(theUfSession!=null){
				success=0;
			}
			UFPart part1 = theUfSession.part();

			if(part1.isLoaded(partFile)!=1){
				OpenData data= part1.open(partFile);
				System.out.println("part is"+ part1);
				partTag=data.part.value; 
				System.out.println("part tag is"+ partTag);
				Tag feature_tag= new Tag(29317);
				UFModeling modeling=theUfSession.modeling();
				Tag bTag = modeling.askFeatBody(feature_tag);
				System.out.println("body tag is"+ bTag);
				double [] loc = modeling.askFeatLocation(feature_tag);
				System.out.println("feature loc is"+ loc[0] + "," + loc[1]);
				UFModeling.AskFeatDirectionData featData = modeling.askFeatDirection(feature_tag);
			}
		} catch (NXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException re) {
			re.printStackTrace();
		}


	}

}
