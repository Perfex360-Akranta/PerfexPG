package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.util.ArrayList;
import java.util.List;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.HorizontalDeploymentBean;
import com.akranta.tpm.dao.KznTlHdmstDao;
import com.akranta.tpm.dao.impl.KznTlHdmstDaoImpl;
import com.akranta.tpm.model.KznTlBestdtl;
import com.akranta.tpm.model.KznTlHdmst;
import com.akranta.tpm.service.KznTlHdmstService;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Validations;

public class KznTlHdMstServiceImpl implements KznTlHdmstService {

	private KznTlHdmstDao kznTlHdmstDao ; 
	private Validations validations ;
	public KznTlHdMstServiceImpl(DBActionTemplate dbActionTemplate)
	{
		kznTlHdmstDao = new KznTlHdmstDaoImpl(dbActionTemplate);
		validations = new Validations();
	}
	
	public void setKznTlMstDao(KznTlHdmstDao kznTlHdmstDao)
	{
		this.kznTlHdmstDao = kznTlHdmstDao;
	}

	@Override
	public KznTlHdmst delete(KznTlHdmst kznTlHdmst) throws Exception {

		return kznTlHdmstDao.delete(kznTlHdmst);
	}
	
	@Override
	public List<KznTlHdmst> delete(List<KznTlHdmst> kznHdmstList) throws Exception {
		// TODO Auto-generated method stub
		
		return this.kznTlHdmstDao.deletekzn(kznHdmstList);
		
	}
			
	public List<KznTlHdmst> save(List<KznTlHdmst> newKznTlHdmst,List<KznTlHdmst> oldKznTlHdmst, HorizontalDeploymentBean horizontalDeploymentBean) throws Exception ,ValidationExceptions
	{
		try
		{
			for( KznTlHdmst kznTlHdmst:newKznTlHdmst)
			{
				CommonMessage.debugMsg("newPlmTlSparedtl in save func"+newKznTlHdmst.size());
				validations.validate(kznTlHdmst,"HorizontalDeployment","save");//com.akranta.validations.tpm.validations.oplcreation.xml - defined rules for server side validations
			}
	
			fillKznHdValues(newKznTlHdmst,oldKznTlHdmst,horizontalDeploymentBean);
			CommonMessage.debugMsg(horizontalDeploymentBean.getFactoryId()+"  horizontalDeploymentBean.getKaizenId()"+horizontalDeploymentBean.getKaizenId());
			
		}
		catch (ValidationExceptions e)
		{
			e.printStackTrace();
			throw new ValidationExceptions(e.getMessage());
		}catch (Exception e)
		{
			e.printStackTrace();
		}
		return  kznTlHdmstDao.save(newKznTlHdmst,horizontalDeploymentBean.getKaizenId());
	}

	@Override
	public List<String[]> select(String khdmKaizenid) throws Exception {
		// TODO Auto-generated method stub
		return kznTlHdmstDao.select( khdmKaizenid);
	}
	public String getcellid(String cellid)throws Exception{
		return kznTlHdmstDao.getcellid(cellid);
	} 

	@Override
	public List<String[]> getAllKznHdDtls(String kaizenId,String sectId,String mchId, String cellId, String dmtid, String dmtlevel) throws Exception {
		// TODO Auto-generated method stub
		return kznTlHdmstDao.getAllKznHdDtls( kaizenId,sectId,mchId,cellId,dmtid,dmtlevel);
	}
	
	@Override
	public List<String[]> FillControlData(String keyid) throws Exception {
		// TODO Auto-generated method stub
		return kznTlHdmstDao.FillControlData(keyid);
	}
	
	/**   For KZN_TL_HDMST TABLE  **/
	private List<KznTlHdmst> fillKznHdValues(List<KznTlHdmst> newKznTlHdmst,List<KznTlHdmst> oldKznTlHdmst, HorizontalDeploymentBean horizontalDeploymentBean)
	{
		String dateTime = CommonFunctions.dateTimeNow();
		String currentDate=CommonFunctions.getDate();
	
		System.out.print("newPlmTlSparedtl"+newKznTlHdmst.size());
		int no=1;
		List<KznTlHdmst> newKznTlHdmstList=new ArrayList<KznTlHdmst>();
		for( KznTlHdmst kznTlHdmst : newKznTlHdmst)
		{	
			CommonMessage.debugMsg("Inside for loop");
			CommonMessage.debugMsg("srvce Created on="+kznTlHdmst.getKhdmCreatedon());
			CommonMessage.debugMsg("srvce keyid ="+kznTlHdmst.getKhdmKeyid());
			kznTlHdmst.setKhdmActive("Y");
		
			if(kznTlHdmst.getKhdmCreatedon() == null )
				kznTlHdmst.setKhdmCreatedon(dateTime);
			else
				kznTlHdmst.setKhdmCreatedon(kznTlHdmst.getKhdmCreatedon());
			
			kznTlHdmst.setKhdmModifiedon(dateTime);
	
			kznTlHdmst.setKhdmKaizenid(horizontalDeploymentBean.getKaizenId());
			
			kznTlHdmst.setKhdmRefdocno(horizontalDeploymentBean.getKaizenId());
			
			kznTlHdmst.setKhdmFactoryid(horizontalDeploymentBean.getFactoryId());
			
			if( kznTlHdmst.getKhdmMachineid() == null )
				kznTlHdmst.setKhdmMachineid("{}");
		
			if( kznTlHdmst.getKhdmLossid() == null )
				kznTlHdmst.setKhdmLossid("{}");
			
			String refdocid= horizontalDeploymentBean.getKaizenId();
			if(refdocid!=null && refdocid.startsWith("MPS"))
				kznTlHdmst.setKhdmRefdoctype("MPS");
			else
				kznTlHdmst.setKhdmRefdoctype("KZN");
			
			String slno=  Integer.toString(no++);
			kznTlHdmst.setKhdmSlno(slno);
			
			if( kznTlHdmst.getKhdmKaizenlink() == null)
				kznTlHdmst.setKhdmKaizenlink("N");
			
			if( kznTlHdmst.getKhdmKaizenlinktype() == null)
				kznTlHdmst.setKhdmKaizenlinktype("{}");
			
			CommonMessage.debugMsg(" newKznTlHdmst.getKhdmAssemblyid()" + kznTlHdmst.getKhdmAssemblyid()+"-"  );
			if( kznTlHdmst.getKhdmAssemblyid() == null)
				kznTlHdmst.setKhdmAssemblyid("{}");
			CommonMessage.debugMsg(" newKznTlHdmst.getKhdmAssemblyid()" + kznTlHdmst.getKhdmAssemblyid()+"-"  );
			
			if( kznTlHdmst.getKhdmPhenomenaid() == null)
				kznTlHdmst.setKhdmPhenomenaid("{}");
			
			if( kznTlHdmst.getKhdmCauseid() == null)
				kznTlHdmst.setKhdmCauseid("{}");
			
			if( kznTlHdmst.getKhdmWoid() == null)
				kznTlHdmst.setKhdmWoid("{}");
			
			if( kznTlHdmst.getKhdmWofeedbackid() == null)
				kznTlHdmst.setKhdmWofeedbackid("{}");
			
			if( kznTlHdmst.getKhdmRemarks() == null)
				kznTlHdmst.setKhdmRemarks("<**>");
			
			if(kznTlHdmst.getKhdmStatus()==null)
				kznTlHdmst.setKhdmStatus("A");
			else 
				kznTlHdmst.setKhdmStatus("C");
			
			if( kznTlHdmst.getKhdmCreatedby() == null)
				kznTlHdmst.setKhdmCreatedby(horizontalDeploymentBean.getCreatedBy());
			
			if(kznTlHdmst.getKhdmCompleteddate()==null)
				kznTlHdmst.setKhdmCompleteddate(currentDate);
			
				kznTlHdmst.setKhdmFactoryid("{}");
			if(kznTlHdmst.getKhdmCompletedby()==null)
				kznTlHdmst.setKhdmCompletedby(horizontalDeploymentBean.getCreatedBy());
			
			CommonMessage.debugMsg(" newKznTlHdmst.getKhdmFactoryid   " + kznTlHdmst.getKhdmFactoryid()+"   -"  );
			newKznTlHdmstList.add(kznTlHdmst);
		}
		CommonMessage.debugMsg("newKznTlHdmst.get(0).getKhdmFactoryid()    "+newKznTlHdmstList.get(0).getKhdmFactoryid());
		return newKznTlHdmstList;
	}

	

	

}
