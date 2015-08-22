package hu.neuron.ier.core;

import hu.neuron.ier.core.dao.ClientDao;
import hu.neuron.ier.core.dao.OfferDao;
import hu.neuron.ier.core.dao.OfferGroupDao;
import hu.neuron.ier.core.dao.RoleDao;
import hu.neuron.ier.core.dao.ShoppingCartDao;
import hu.neuron.ier.core.dao.UserDao;
import hu.neuron.ier.core.entity.Client;
import hu.neuron.ier.core.entity.Offer;
import hu.neuron.ier.core.entity.OfferGroup;
import hu.neuron.ier.core.entity.Role;
import hu.neuron.ier.core.entity.ShoppingCart;
import hu.neuron.ier.core.entity.User;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class CreateShema {
	private static final Logger logger = Logger.getLogger(CreateShema.class);

	@Autowired
	public RoleDao roleDAO;
	@Autowired
	public UserDao userDAO;
	@Autowired
	public OfferDao offerDAO;
	@Autowired
	public OfferGroupDao offerGroupDAO;
	@Autowired
	public ShoppingCartDao shoppingCartDao;
	@Autowired
	public ClientDao clientDao;

	public void insertRoles() {
		Role dto = null;
		try {
			if (roleDAO.findRoleByName("ROLE_USER") == null) {
				dto = new Role();
				dto.setName("ROLE_USER");
				roleDAO.save(dto);
			}
			if (roleDAO.findRoleByName("ROLE_ADMIN") == null) {
				dto = new Role();
				dto.setName("ROLE_ADMIN");
				roleDAO.save(dto);
			}
			if (roleDAO.findRoleByName("ROLE_CLIENT") == null) {
				dto = new Role();
				dto.setName("ROLE_CLIENT");
				roleDAO.save(dto);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void insertUsersAndAddRole() {
		try {
			if (userDAO.findUserByName("manager") == null) {
				List<Role> roles = new ArrayList<Role>();
				Role role;
				User dto = new User();
				dto.setUserName("manager");
				dto.setPassword("pass");
				dto.setEmail("manager@email.hu");
				dto.setPhone("4421123");
				dto.setFullName("Full Name");

				role = roleDAO.findRoleByName("ROLE_ADMIN");
				roles.add(role);
				role = roleDAO.findRoleByName("ROLE_USER");
				roles.add(role);

				dto.setRoles(roles);
				userDAO.save(dto);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void saveOffers() {

		try {
			if (offerDAO.findOfferByName("LenovoOffer").isEmpty()) {

				OfferGroup offerGroup = new OfferGroup();
				OfferGroup offerGroup1 = new OfferGroup();
				OfferGroup offerGroup2 = new OfferGroup();
				OfferGroup offerGroup3 = new OfferGroup();

				offerGroup.setName("Lap-topok");
				offerGroup1.setName("Telefonok");
				offerGroup2.setName("Akkumulátor Töltö");
				offerGroup3.setName("Elektronikus eszközök"); // parent

				offerGroup.setDescription("Hordozható számítógépek");
				offerGroup1.setDescription("Okos és nem okos telefonok");
				offerGroup2.setDescription("Akkumulátor töltésre használatos eszközök");
				offerGroup3.setDescription("Elektronikusan müködő eszközök");

				offerGroup = offerGroupDAO.save(offerGroup);
				offerGroup1 = offerGroupDAO.save(offerGroup1);
				offerGroup2 = offerGroupDAO.save(offerGroup2);
				offerGroup3 = offerGroupDAO.save(offerGroup3);

				offerGroup.setParentOfferGroup(offerGroup3);
				offerGroup1.setParentOfferGroup(offerGroup3);
				offerGroup2.setParentOfferGroup(offerGroup3);

				Offer dto = new Offer();
				dto.setDescription("1 db Lenovo laptop");
				dto.setFeatured(true);
				dto.setName("LenovoOffer");
				dto.setOriginalCost(140000L);
				dto.setNewCost(120000L);
				dto.setAction(false);

				dto.setParentOfferGroup(offerGroup);
				offerDAO.save(dto);

				Offer dto1 = new Offer();
				dto1.setDescription("1 db iPhone okostelefon ajándék tokkal");
				dto1.setFeatured(true);
				dto1.setName("iPhoneOffer");
				dto1.setOriginalCost(130000L);
				dto1.setNewCost(120000L);
				dto1.setAction(true);
				dto1.setParentOfferGroup(offerGroup1);
				offerDAO.save(dto1);

				Offer dto2 = new Offer();
				dto2.setDescription("2 db Nexus telefon töltövel");
				dto2.setFeatured(true);
				dto2.setName("NexusOffer");
				dto2.setOriginalCost(80000L);
				dto2.setNewCost(70000L);
				dto2.setAction(false);
				dto2.setParentOfferGroup(offerGroup1);
				offerDAO.save(dto2);

				Offer dto3 = new Offer();
				dto3.setDescription("1 db Hama akkumulátor töltö 4 db elemmel");
				dto3.setFeatured(true);
				dto3.setName("HamaOffer");
				dto3.setOriginalCost(4000L);
				dto3.setNewCost(3000L);
				dto3.setAction(false);
				dto3.setParentOfferGroup(offerGroup2);
				offerDAO.save(dto3);
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public void insertClientsAndAddRole() {
		try {
			if (clientDao.findByUserName("asdf") == null) {
				Role role = roleDAO.findRoleByName("ROLE_CLIENT");
				List<Role> roles = new ArrayList<Role>();
				roles.add(role);
				ShoppingCart shoppingCart = new ShoppingCart();
				shoppingCart = shoppingCartDao.save(shoppingCart);

				Client client = new Client();
				client.setEmail("email");
				client.setFullName("fullName");
				client.setPassword("asdf");
				client.setPhone("123");
				client.setRoles(roles);
				client.setShoppingCart(shoppingCart);
				client.setUserName("asdf");

				clientDao.save(client);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
}
