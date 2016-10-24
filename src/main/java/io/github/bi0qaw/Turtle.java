package io.github.bi0qaw;

import ch.njol.skript.lang.function.Function;
import io.github.bi0qaw.util.Matrix3;
import io.github.bi0qaw.util.VectorMath;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.UUID;


public class Turtle implements Runnable {

	private final UUID id = UUID.randomUUID();
	private Vector position = new Vector(0,0,0);
	private Vector u = new Vector(1,0,0), v = new Vector(0,1,0), w = new Vector(0,0,1);
	private Matrix3 inverseMatrix = new Matrix3(u,v,w);
	private boolean matrixIsDirty = false;
	private World world;
	private String name;
	private boolean isFree = false;
	private int heartbeat = 1;
	private int task = -1;
	private boolean followRotation = false;
	private Turtle parent;
	private ArrayList<Turtle> children = new ArrayList<Turtle>();
	private Function<?> function;
	private Object[][] parameters;

	private static final Vector V_ZERO = new Vector();

	public Turtle(Location l){
		TurtleManager.register(this);
		setLocation(l);
	}

	public void setLocation(Location location) {
		Vector l1 = location.toVector();
		Vector delta = l1.subtract(getPosition());
		position = position.add(delta);
		setWorld(location.getWorld());
	}

	public Location getLocation() {
		Vector p = getPosition();
		return new Location(getWorld(), p.getX(), p.getY(), p.getZ(), 0, 0);
	}

	public void rotate(Vector axis, double angle) {
		u = VectorMath.rot(getU(), axis, angle);
		v = VectorMath.rot(getV(), axis, angle);
		w = VectorMath.rot(getW(), axis, angle);
		matrixIsDirty = true;
		if (!children.isEmpty()) {
			for (Turtle c : children){
				if (c.isFollowRotation()){
					c.rotate(axis, angle);
				}
			}
		}
	}

	public void rotateU(double u) {
		this.v = VectorMath.rot(getV(), getU(), u);
		this.w = VectorMath.rot(getW(), getU(), u);
		matrixIsDirty = true;
		if (!children.isEmpty()) {
			for (Turtle c : children){
				if(c.isFollowRotation()){
					c.rotateU(u);
				}
			}
		}
	}

	public void rotateV(double v) {
		this.u = VectorMath.rot(getU(), getV(), v);
		this.w = VectorMath.rot(getW(), getV(), v);
		matrixIsDirty = true;
		if (!children.isEmpty()) {
			for (Turtle c : children){
				if(c.isFollowRotation()){
					c.rotateV(v);
				}
			}
		}
	}

	public void rotateW(double w) {
		this.u = VectorMath.rot(getU(), getW(), w);
		this.v = VectorMath.rot(getV(), getW(), w);
		matrixIsDirty = true;
		if (!children.isEmpty()) {
			for (Turtle c : children){
				if(c.isFollowRotation()){
					c.rotateW(w);
				}
			}
		}
	}

	public double getYaw() {
		Float pitch = (float) getPitch();
		if (pitch.equals((float) VectorMath.HALF_PI) || pitch.equals((float) -VectorMath.HALF_PI)) {
			return 0;
		}
		return Math.atan2(w.getX(), u.getX());
	}

	public double getPitch() {
		return Math.atan2(-w.getY(), Math.sqrt(u.getX() * u.getX() + w.getX() * w.getX()));
	}

	public double getRoll() {
		Float pitch = (float) getPitch();
		if (pitch.equals((float) VectorMath.HALF_PI)){
			return Math.atan2(u.getZ(), w.getZ());
		} else if (pitch.equals((float) -VectorMath.HALF_PI)) {
			return -Math.atan2(u.getZ(), w.getZ());
		}
		return Math.atan2(v.getZ(), v.getY());
	}

	public void resetRotation() {
		u.setX(1).setY(0).setZ(0);
		v.setX(0).setY(1).setZ(0);
		w.setX(0).setY(0).setZ(1);
		matrixIsDirty = true;
		if (!children.isEmpty()) {
			for (Turtle c : children){
				if(c.isFollowRotation()){
					c.resetRotation();
				}
			}
		}
	}

	public void move(double u, double v, double w){
		moveU(u);
		moveV(v);
		moveW(w);
	}

	public boolean hasParent() {
		return parent != null ? true : false;
	}

	public Function<?> getFunction() {
		return function;
	}

	public void setFunction(Function<?> function) {
		this.function = function;
	}

	public Object[][] getParameters() {
		return parameters;
	}

	public void setParameters(Object[][] parameters) {
		this.parameters = parameters;
	}

	public boolean isFollowRotation() {
		return followRotation;
	}

	public void setFollowRotation(boolean followRotation) {
		this.followRotation = followRotation;
	}

	public Vector getU() {
		return u.clone();
	}

	public Vector getV() {
		return v.clone();
	}

	public Vector getW() {
		return w.clone();
	}

	public void moveU(double n) {
		position.add(getU().multiply(n));
	}

	public void moveV(double n) {
		position.add(getV().multiply(n));
	}

	public void moveW(double n) {
		position.add(getW().multiply(n));
	}

	public void setU(Vector u) {
		if (!u.equals(V_ZERO)) {
			Vector future = u.clone().normalize();
			if (future.equals(this.u)){
				return;
			}
			Vector axis = getU().getCrossProduct(future);
			double angle = getU().angle(future) * VectorMath.RAD_TO_DEG;
			this.u = future;
			this.v = VectorMath.rot(v, axis, angle);
			this.w = VectorMath.rot(w, axis, angle);
		}
	}

	public void setV(Vector v) {
		if (!v.equals(V_ZERO)) {
			Vector future = v.clone().normalize();
			if (future.equals(this.v)){
				return;
			}
			Vector axis = getV().getCrossProduct(future);
			double angle = getV().angle(future) * VectorMath.RAD_TO_DEG;
			this.v = future;
			this.u = VectorMath.rot(u, axis, angle);
			this.w = VectorMath.rot(w, axis, angle);
		}
	}

	public void setW(Vector w) {

		if (!w.equals(V_ZERO)) {
			Vector future = w.clone().normalize();
			if (future.equals(this.w)){
				return;
			}
			Vector axis = getW().getCrossProduct(future);
			double angle = getW().angle(future) * VectorMath.RAD_TO_DEG;
			this.w = future;
			this.u = VectorMath.rot(u, axis, angle);
			this.v = VectorMath.rot(v, axis, angle);
		}
	}

	public boolean isFree() {
		return isFree;
	}

	protected void setIsFree(boolean b) {
		isFree = b;
	}

	public int getHeartbeat() {
		return heartbeat;
	}

	public void setHeartbeat(int heartbeat) {
		if (heartbeat == 0) {
			this.heartbeat = 1;
		}
		else {
			this.heartbeat = heartbeat;
		}
	}

	public int getTask() {
		return task;
	}

	protected void setTask(int task) {
		this.task = task;
	}

	public Vector getPosition() {
		if (hasParent()) {
			return parent.getPosition(position);
		}
		return position;
	}

	public Vector getPosition(Vector d) {
		return getPosition(d.getX(), d.getY(), d.getZ());
	}

	public Vector getPosition(double u, double v, double w) {
		Vector p = getPosition().clone();
		p.add(getU().clone().multiply(u));
		p.add(getV().clone().multiply(v));
		p.add(getW().clone().multiply(w));
		return p;
	}

	public void setPosition(Vector position) {
		this.position = position;
	}

	public ArrayList<Turtle> getChildren() {
		return children;
	}

	private void addChild(Turtle turtle) {
		turtle.setParent(this);
	}

	public boolean hasChild(Turtle child){
		for (Turtle t : getChildren()){
			if (t.equals(child)) return true;
		}
		return false;
	}

	public boolean hasDescendant(Turtle descendant){
		for (Turtle t : getDescendants()){
			if (t.equals(descendant)) return true;
		}
		return false;
	}

	public ArrayList<Turtle> getDescendants() {
		ArrayList<Turtle> descendants = new ArrayList<Turtle>();
		if (!children.isEmpty()) {
			for (Turtle c : children){
				descendants.add(c);
				descendants.addAll(c.getDescendants());
			}
		}
		return descendants;
	}

	public UUID getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Turtle getParent() {
		return parent;
	}

	public void setParent(Turtle parent) {
		if (parent.getDescendants().contains(this) || this.getDescendants().contains(parent)){
			return;
		}

		Vector currentPos = getPosition();
		Vector parentPos = parent.getPosition();
		parent.updateMatrix();
		position = parent.getInverseMatrix().mul(currentPos.subtract(parentPos));

		if (isFollowRotation()){
			u = parent.getU();
			v = parent.getV();
			w = parent.getW();
			matrixIsDirty = true;
		}
		if (hasParent()){
			getParent().children.remove(this);
		}
		parent.children.add(this);
		this.parent = parent;
		if (!children.isEmpty()){
			for (Turtle t : children){
				t.setParent(this);
			}
		}
	}

	public void removeParent(){
		position = getPosition();
		parent = null;
	}

	public World getWorld() {
		return world;
	}

	public void teleport(Location location) {
		setLocation(location);
	}

	public void setWorld(World world) {
		this.world = world;
	}

	public Matrix3 getInverseMatrix() {
		return inverseMatrix;
	}

	public boolean isMatrixIsDirty() {
		return matrixIsDirty;
	}

	public void setMatrixIsDirty(boolean matrixIsDirty) {
		this.matrixIsDirty = matrixIsDirty;
	}

	public void updateMatrix(){
		if (isMatrixIsDirty()){
			inverseMatrix = new Matrix3(getU(), getV(), getW());
			inverseMatrix.invert();
			setMatrixIsDirty(false);
		}
	}

	public void remove(){
		this.stop();
		TurtleManager.remove(this);
	}

	public void removeWithChildren() {
		this.stopWithChildren();
		TurtleManager.remove(this);
		for (Turtle t : getChildren()) {
			t.remove();
		}
	}

	public void removeWithDescendants() {
		this.stopWithDescendants();
		TurtleManager.remove(this);
		for (Turtle t : getDescendants()){
			t.remove();
		}
	}

	public void release(){
		if (!isFree){
			TurtleManager.release(this);
			isFree = true;
		}
	}

	public void releaseWithChildren() {
		release();
		for (Turtle t : getChildren()){
				t.release();
		}
	}

	public void releaseWithDescendants(){
		release();
		for (Turtle t: getDescendants()){
			t.release();
		}
	}

	public void stop(){
		if (isFree) {
			TurtleManager.stop(this);
			isFree = false;
		}
	}

	public void stopWithChildren() {
		stop();
		for (Turtle t : getChildren()){
			t.stop();
		}
	}

	public void stopWithDescendants() {
		stop();
		for (Turtle t : getDescendants()){
			t.stopWithDescendants();
		}
	}

	public void run() {
		if (function != null) {
			function.execute(parameters);
		}
	}
}
