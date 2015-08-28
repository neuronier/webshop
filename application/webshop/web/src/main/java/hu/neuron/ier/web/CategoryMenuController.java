package hu.neuron.ier.web;

import hu.neuron.ier.business.offer.OfferServiceRemote;
import hu.neuron.ier.business.offergroup.OfferGroupServiceRemote;
import hu.neuron.ier.business.vo.OfferGroupVO;
import hu.neuron.ier.business.vo.OfferVO;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuElement;
import org.primefaces.model.menu.MenuModel;

@SessionScoped
@ManagedBean(name = "categoryMenuController")
public class CategoryMenuController implements Serializable {

	private static final long serialVersionUID = -9124689148741262737L;
	private MenuModel model;
	private List<OfferVO> offers;
	private Long selectedId;
	
	@ManagedProperty("#{dataGridView}")
	private DataGridView dataGridView;

	@EJB(name = "OfferGroupService", mappedName = "OfferGroupService")
	OfferGroupServiceRemote offerGroupServiceRemote;
	
	@EJB(name = "OfferService", mappedName = "OfferService")
	OfferServiceRemote offerService;

	public List<OfferVO> getOffers() {
		return offers;
	}

	public void setOffers(List<OfferVO> offers) {
		this.offers = offers;
	}

	public MenuModel getModel() {
		return model;
	}

	public void setModel(MenuModel model) {
		this.model = model;
	}

	@PostConstruct
	public void init() {
		model = new DefaultMenuModel();

		DefaultSubMenu fo = new DefaultSubMenu();
		fo.setLabel("Főoldal");
		DefaultMenuItem foOldal = new DefaultMenuItem();
		foOldal.setValue(getClass().getResourceAsStream("Főoldal"));
		foOldal.setUrl("/");
		foOldal.setUpdate(":form");
		fo.addElement(foOldal);
		model.addElement(fo);
		createMenu(null, null);
	}
	//belül konvertálom a paramétert Long típusúvá
	public String findOffers(String offerGroupId) {
		Long id = Long.valueOf(offerGroupId);
		try {
			getDataGridView().setOffers( offerService.getOffersByParentOfferGroup(id));
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return "/";
	}

	public void createMenu(MenuElement elem, OfferGroupVO parentOfferGroup) {
		// össszes közvetlen gyermek lekérdezése
		try {
//			List<OfferGroupVO> lista = offerGroupServiceRemote
//					.findOfferGroupByParentOfferGroup(parentOfferGroup);
			List<OfferGroupVO> lista = offerGroupServiceRemote
					.findOfferGroupByParentOfferGroupAndActive(parentOfferGroup,true);
			MenuElement element;
			// ha nincs gyermek vége
			if (lista.size() == 0) {
				return;
			} else { // különben bejárjuk a gyermekek listáját
				for (OfferGroupVO ogvo : lista) {
					// megnézzük hogy van- e gyermeke
				
//					List<OfferGroupVO> gyerekek = offerGroupServiceRemote
//							.findOfferGroupByParentOfferGroupAndActive(ogvo,true);
					int gyerekekSzama = offerGroupServiceRemote.countOfferGroupByParentOfferGroupAndActive(ogvo, true);
//					if (gyerekek.size() > 0) {
					if(gyerekekSzama > 0){
						// ha van akkor DefaultSubmenu-t kell készíteni
						element = new DefaultSubMenu(ogvo.getName());
						element.setId(ogvo.getId().toString());
						
						// ha a paraméterként kapott elem null volt, akkor a
						// modell-hez adjuk hozzá, mert főmenüpont
						if (elem == null) {
							model.addElement(element);
							// különben a paraméterként kapott elemhez kell
							// hozzáadni, ami csak DefaultSubmenu típusú lehet
						} else {
							((DefaultSubMenu) elem).addElement(element);
						}
					} else {
						// Ha nem volt gyermeke, de akkor almenüpont lesz
						// De nincs szülője
						if (elem == null) {
							element = new DefaultSubMenu(ogvo.getName());
							// beállítjuk az id-ját
							element.setId(ogvo.getId().toString());
							
							// hozzáadjuk a modellhez
							model.addElement(element);
						} else {
							element = new DefaultMenuItem(ogvo.getName());
							element.setId(ogvo.getId().toString());
							((DefaultMenuItem) element).setCommand("#{categoryMenuController.findOffers(" + ogvo.getId().toString() + ")}");
							((DefaultMenuItem) element).setUpdate(":form");
		
							
							
							// hozzáadjuk a szülőhöz
							// ha van szülője
							if (elem instanceof DefaultSubMenu) {
								// hozzáadjuk a paraméterként kapott elemhez
								((DefaultSubMenu) elem).addElement(element);
							}
						}

					}
					// meghívjuk a metódust a létrehozott elemre
					createMenu(element, ogvo);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Long getSelectedId() {
		return selectedId;
	}

	public void setSelectedId(Long selectedId) {
		this.selectedId = selectedId;
	}

	public DataGridView getDataGridView() {
		return dataGridView;
	}

	public void setDataGridView(DataGridView dataGridView) {
		this.dataGridView = dataGridView;
	}
}
