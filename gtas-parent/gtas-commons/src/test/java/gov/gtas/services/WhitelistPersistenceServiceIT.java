/*
 * All GTAS code is Copyright 2016, Unisys Corporation.
 * 
 * Please see LICENSE.txt for details.
 */
package gov.gtas.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import gov.gtas.config.CachingConfig;
import gov.gtas.config.CommonServicesConfig;
import gov.gtas.model.Whitelist;
import gov.gtas.vo.WhitelistVo;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { CommonServicesConfig.class,
		CachingConfig.class })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class WhitelistPersistenceServiceIT {

	@Autowired
	private WhitelistService whitelistService;

	private WhitelistVo testwlv;

	@Before
	public void setUp() throws Exception {
		testwlv = createWhitelistTestData();
	}

	@Transactional
	@Test()
	public void testGetAllWhitelists() {
		whitelistService.create(testwlv, "test");
		List<WhitelistVo> wlvs = whitelistService.getAllWhitelists();
		assertNotNull(wlvs);
	}

	@Transactional
	@Test()
	public void testCreateWhitelist() {
		Whitelist wl = whitelistService.create(testwlv, "test");
		assertNotNull(wl);
	}

	@Transactional
	@Test()
	public void testDeleteWhitelist() {
		Whitelist wl = whitelistService.create(testwlv, "test");
		List<WhitelistVo> uWlvs = whitelistService.getAllWhitelists();
		assertNotNull(uWlvs);
		whitelistService.delete(wl.getId(), "test");
		List<WhitelistVo> uWlvs2 = whitelistService.getAllWhitelists();
		assertTrue(uWlvs2.isEmpty());
	}

	@Transactional
	@Test()
	public void testUpdateWhitelist() {
		Whitelist wl = whitelistService.create(testwlv, "test");
		List<WhitelistVo> wlvs = whitelistService.getAllWhitelists();
		WhitelistVo rWlv = wlvs.get(0);
		rWlv.setId(wl.getId());
		rWlv.setDocumentNumber("222");
		whitelistService.update(rWlv, "test");
		List<WhitelistVo> uWlvs = whitelistService.getAllWhitelists();
		assertNotNull(uWlvs);
		WhitelistVo rWlv2 = uWlvs.get(0);
		assertEquals("222", rWlv2.getDocumentNumber());
	}

	private WhitelistVo createWhitelistTestData() {
		WhitelistVo wlv = new WhitelistVo();
		wlv.setFirstName("jj");
		wlv.setMiddleName("jMiddle");
		wlv.setLastName("Palazio");
		wlv.setDob(new Date());
		wlv.setGender("M");
		wlv.setCitizenshipCountry("USA");
		wlv.setDocumentNumber("111");
		wlv.setDocumentType("P");
		wlv.setExpirationDate(new Date());
		wlv.setIssuanceCountry("USA");
		wlv.setIssuanceDate(new Date());
		wlv.setResidencyCountry("USA");
		return wlv;
	}

}
