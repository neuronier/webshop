package hu.neuron.ier.web;

import hu.neuron.ier.business.offergroup.OfferGroupServiceRemote;
import hu.neuron.ier.business.vo.OfferGroupVO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.TreeDragDropEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

@ManagedBean(name = "treeDNDView")
@ViewScoped
public class DragDropView implements Serializable {

	private static final long serialVersionUID = -4797994203324046438L;

	@EJB(name = "OfferGroupService", mappedName = "OfferGroupService")
	OfferGroupServiceRemote offerGroupServiceRemote;

	private TreeNode root1;

	private TreeNode[] tree;

	public TreeNode[] getTree() {
		return tree;
	}

	public void setTree(TreeNode[] tree) {
		this.tree = tree;
	}

	private TreeNode root2;

	private TreeNode selectedNode1;

	private TreeNode selectedNode2;

	private List<TreeNode> allNode;

	private List<OfferGroupVO> allOfferGroup;

	public List<TreeNode> getAllNode() {
		return allNode;
	}

	public void setAllNode(List<TreeNode> allNode) {
		this.allNode = allNode;
	}

	@PostConstruct
	public void init() {
		root1 = new DefaultTreeNode(null, null);
		
		root2 = new DefaultTreeNode(null, null);
		allNode = new ArrayList<TreeNode>();
		createTree(null, root1);
	}

	public void getAllOfferGroup() {
		try {
			allOfferGroup = offerGroupServiceRemote.findAllOfferGroup();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<OfferGroupVO> getAllParentOfferGroup() {
		List<OfferGroupVO> parents = new ArrayList<OfferGroupVO>();
		try {
			parents = offerGroupServiceRemote.findAllParentOfferGroups();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return parents;
	}
	
	public void createTree(OfferGroupVO parentOfferGroup, TreeNode parentNode){
		try {
			List<OfferGroupVO> lista = offerGroupServiceRemote.findOfferGroupByParentOfferGroup(parentOfferGroup);
			TreeNode node;
			if(lista.size() == 0){
				return;
			}
			else{
				for(OfferGroupVO ogv : lista){
					node = new DefaultTreeNode(ogv, parentNode);
					allNode.add(node);
					createTree(ogv, node);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public TreeNode getRoot1() {
		return root1;
	}

	public TreeNode getRoot2() {
		return root2;
	}

	public TreeNode getSelectedNode1() {
		return selectedNode1;
	}

	public void setSelectedNode1(TreeNode selectedNode1) {
		this.selectedNode1 = selectedNode1;
	}

	public TreeNode getSelectedNode2() {
		return selectedNode2;
	}

	public void setSelectedNode2(TreeNode selectedNode2) {
		this.selectedNode2 = selectedNode2;
	}

	public void onDragDrop(TreeDragDropEvent event) {
		TreeNode dragNode = event.getDragNode();
		TreeNode dropNode = event.getDropNode();
		int dropIndex = event.getDropIndex();
		OfferGroupVO dragged = ((OfferGroupVO) dragNode.getData());
		OfferGroupVO parent = (OfferGroupVO) (dropNode.getData());
		try {
			if (parent != null) {
				dragged = offerGroupServiceRemote.offerGroupToOfferGroup(
						dragged.getId(), parent.getId());
			}
			else{
				dragged.setParentOfferGroup(null);
				dragged = offerGroupServiceRemote.createOfferGroup(dragged);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Dragged " + dragNode.getData(), "Dropped on "
						+ dropNode.getData() + " at " + dropIndex);
		FacesMessage message2 = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Dragged instance of: ", dragged.getClass().getSimpleName());
		FacesContext.getCurrentInstance().addMessage(null, message);
		FacesContext.getCurrentInstance().addMessage(null, message2);

	}
}