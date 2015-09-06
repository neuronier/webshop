package hu.neuron.ier.business.offergroup;

import hu.neuron.ier.business.converter.OfferGroupConverter;
import hu.neuron.ier.business.vo.OfferGroupVO;
import hu.neuron.ier.core.dao.OfferDao;
import hu.neuron.ier.core.dao.OfferGroupDao;
import hu.neuron.ier.core.entity.Offer;
import hu.neuron.ier.core.entity.OfferGroup;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

@Stateless(mappedName = "OfferGroupService", name = "OfferGroupService")
@Remote(OfferGroupServiceRemote.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Interceptors(SpringBeanAutowiringInterceptor.class)
public class OfferGroupServiceImpl implements OfferGroupServiceRemote,
		Serializable {

	private static final long serialVersionUID = -5417875507641978550L;

	@Autowired
	OfferGroupDao offerGroupDao;

	@Autowired
	OfferDao offerDao;

	@EJB
	OfferGroupConverter converter;

	@Override
	public OfferGroupVO createOfferGroup(OfferGroupVO offerGroupVO)
			throws Exception {
		OfferGroupVO vo = converter.toVO(offerGroupDao.save(converter
				.toEntity(offerGroupVO)));
		return vo;
	}

	@Override
	public void deleteOfferGroup(Long id) throws Exception {
		OfferGroup og = offerGroupDao.findOne(id);
		// függőségek megszüntetése
		// gyermek offergroup elemek összegyűjtése
		List<OfferGroup> childOffergroups = offerGroupDao
				.findOfferGroupByParentOfferGroup(og);
		// gyermekek szülőmezőjének nullázása
		for (OfferGroup ogroup : childOffergroups) {
			ogroup.setParentOfferGroup(null);
			offerGroupDao.save(ogroup);
		}

		// gyermek offer elemek összegyűjtése
		List<Offer> childOffers = offerDao.findOfferByParentOfferGroup(og);
		// gyermekek szülőmezőjének nullázása
		for (Offer o : childOffers) {
			o.setParentOfferGroup(null);
			offerDao.save(o);
		}
		// mentés, hogy az adatbázisban is változzon
		offerGroupDao.save(og);
		// törlés függőségek nélkül
		offerGroupDao.delete(og);
	}

	@Override
	public OfferGroupVO updateOfferGroupName(Long id, String name)
			throws Exception {
		OfferGroup og = offerGroupDao.findOne(id);
		og.setName(name);
		OfferGroupVO vo = converter.toVO(offerGroupDao.save(og));
		return vo;
	}

	@Override
	public OfferGroupVO updateOfferGroupDescription(Long id, String description)
			throws Exception {
		OfferGroup og = offerGroupDao.findOne(id);
		og.setDescription(description);
		OfferGroupVO vo = converter.toVO(offerGroupDao.save(og));
		return vo;
	}

	@Override
	public OfferGroupVO offerGroupToOfferGroup(Long id, Long parentId)
			throws Exception {
		OfferGroup parent = offerGroupDao.findOne(parentId);
		OfferGroup og = offerGroupDao.findOne(id);
		og.setParentOfferGroup(parent);
		OfferGroupVO vo = converter.toVO(offerGroupDao.save(og));
		return vo;
	}

	@Override
	public OfferGroupVO offerToOfferGroup(Long offerId, Long offerGroupId)
			throws Exception {

		Offer offer = offerDao.findOne(offerId);
		OfferGroup og = offerGroupDao.findOne(offerGroupId);
		offer.setParentOfferGroup(og);
		offerDao.save(offer);
		OfferGroupVO vo = converter.toVO(offerGroupDao.save(og));
		return vo;
	}

	@Override
	public List<OfferGroupVO> findAllOfferGroup() throws Exception {
		return converter.toVO(offerGroupDao.findAll());
	}

	@Override
	public List<OfferGroupVO> searchOfferGroups(String key) throws Exception {
		return converter.toVO(offerGroupDao.searchOfferGroup(key));
	}

	/**
	 * Metódus az összes olyan ajánlatcsoport lekérdezéséhez, aminek nincs szülő
	 * csoportja
	 */
	@Override
	public List<OfferGroupVO> findAllParentOfferGroups() throws Exception {

		return converter.toVO(offerGroupDao
				.findOfferGroupByParentOfferGroup(null));
	}

	@Override
	public List<OfferGroupVO> findOfferGroupByParentOfferGroup(
			OfferGroupVO parentOfferGroup) throws Exception {

		return converter.toVO(offerGroupDao
				.findOfferGroupByParentOfferGroup(converter
						.toEntity(parentOfferGroup)));
	}

	@Override
	public List<OfferGroupVO> findOfferGroupByParentOfferGroupAndActive(
			OfferGroupVO parentOfferGroup, Boolean active) throws Exception {

		return converter.toVO(offerGroupDao
				.findOfferGroupByParentOfferGroupAndActive(
						converter.toEntity(parentOfferGroup), active));
	}

	/**
	 * Metódus egy ajánlatcsoport aktív tulajdonságának beállításához
	 * rekurzívan. Beállítja a paraméterként kapott ajánlatcsoport aktív
	 * tulajdonságát úgy, hogy az összes gyermekének is beállítja az aktív
	 * tulajdonságát, és a gyermekek gyermekének is rekurzívan
	 * 
	 * @param offerGroup
	 *            A beállítandó ajánlatcsoport
	 * @param active
	 *            Aktív legyen-e
	 * @throws Exception
	 */
	@Override
	public void updateOfferGroupActiveRecursively(OfferGroupVO offerGroup,
			Boolean active) throws Exception {
		// az átadott offergroup aktív tulajdonságát beállítjuk
		offerGroup.setActive(active);
		// mentjük
		offerGroupDao.save(converter.toEntity(offerGroup));
		// lekérdezzük, van-e gyermeke aminek az aktív tulajdonsága még nincs
		// átállítva
		int childCount = offerGroupDao
				.countOfferGroupByParentOfferGroupAndActive(
						converter.toEntity(offerGroup), !active);
		if (childCount == 0) {
			// ha nincs gyermeke vége
			return;
		} else {
			// különben lekérdezzük a gyermekeit
			List<OfferGroupVO> childs = findOfferGroupByParentOfferGroup(offerGroup);
			// bejárjuk a gyermekek listáját

			for (OfferGroupVO ogvo : childs) {
				// meghívjuk az adott gyermekre is a metódust
				updateOfferGroupActiveRecursively(ogvo, active);
			}
		}
	}

	/**
	 * Metódus egy ajánlatcsoport közvetlen gyermekeinek megszámlálásához,
	 * amelyek a paraméterként kapott aktív tulajdonsággal rendelkeznek.
	 * 
	 * @param parentOfferGroup
	 *            a szülő ajánlatcsoport
	 * @param active
	 *            milyen ajánlatcsoportokat akarunk megszámolni
	 * 
	 * @return Egész számmal tér vissza, a gyermek ajánlatcsoportok számával
	 */
	@Override
	public Integer countOfferGroupByParentOfferGroupAndActive(
			OfferGroupVO parentOfferGroup, Boolean active) throws Exception {

		return offerGroupDao.countOfferGroupByParentOfferGroupAndActive(
				converter.toEntity(parentOfferGroup), active);
	}

	@Override
	public OfferGroupVO getOfferGroup(Long id) throws Exception {
		OfferGroupVO offerGroup;
		offerGroup = converter.toVO(offerGroupDao.findOne(id));
		return offerGroup;
	}

}
