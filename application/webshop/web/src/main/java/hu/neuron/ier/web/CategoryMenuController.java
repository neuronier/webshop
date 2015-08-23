package hu.neuron.ier.web;

import hu.neuron.ier.business.offergroup.OfferGroupServiceRemote;
import hu.neuron.ier.business.vo.OfferGroupVO;
import hu.neuron.ier.business.vo.OfferVO;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuElement;
import org.primefaces.model.menu.MenuModel;

@ViewScoped
@ManagedBean(name = "categoryMenuController")
public class CategoryMenuController implements Serializable {

	private static final long serialVersionUID = -9124689148741262737L;
	private MenuModel model;
	private List<OfferVO> offers;

	@EJB(name = "OfferGroupService", mappedName = "OfferGroupService")
	OfferGroupServiceRemote offerGroupServiceRemote;

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

		// // First submenu
		// DefaultSubMenu firstSubmenu = new DefaultSubMenu("Dynamic Submenu");
		//
		// DefaultMenuItem item = new DefaultMenuItem("External");
		// item.setUrl("http://www.primefaces.org");
		// item.setIcon("ui-icon-home");
		// firstSubmenu.addElement(item);
		// DefaultSubMenu ujmenu = new DefaultSubMenu("Új menü");
		// DefaultMenuItem item2 = new DefaultMenuItem("Uj almenu");
		// item.setUrl("http://www.primefaces.org");
		// item.setIcon("ui-icon-home");
		// ujmenu.addElement(item2);
		// firstSubmenu.addElement(item);
		// firstSubmenu.addElement(ujmenu);
		//
		// model.addElement(firstSubmenu);
		//
		// // Second submenu
		// DefaultSubMenu secondSubmenu = new DefaultSubMenu("Dynamic Actions");
		//
		// item = new DefaultMenuItem("Save");
		// item.setIcon("ui-icon-disk");
		// // item.setCommand("#{menuView.save}");
		// // item.setUpdate("messages");
		// secondSubmenu.addElement(item);
		//
		// item = new DefaultMenuItem("Delete");
		// item.setIcon("ui-icon-close");
		// // item.setCommand("#{menuView.delete}");
		// item.setAjax(false);
		// secondSubmenu.addElement(item);
		//
		// item = new DefaultMenuItem("Redirect");
		// item.setIcon("ui-icon-search");
		// // item.setCommand("#{menuView.redirect}");
		//
		// secondSubmenu.addElement(item);
		//
		// model.addElement(secondSubmenu);
		createMenu(null, null);
	}

	public void findOffers(Long offerGroupId) {

	}

	public void createMenu(MenuElement elem, OfferGroupVO parentOfferGroup) {
		// össszes közvetlen gyermek lekérdezése
		try {
			List<OfferGroupVO> lista = offerGroupServiceRemote
					.findOfferGroupByParentOfferGroup(parentOfferGroup);
			MenuElement element;
			// ha nincs gyermek vége
			if (lista.size() == 0) {
				return;
			} else { // különben bejárjuk a gyermekek listáját
				for (OfferGroupVO ogvo : lista) {
					// megnézzük hogy van- e gyermeke
					List<OfferGroupVO> gyerekek = offerGroupServiceRemote
							.findOfferGroupByParentOfferGroup(ogvo);
					if (gyerekek.size() > 0) {
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
}
