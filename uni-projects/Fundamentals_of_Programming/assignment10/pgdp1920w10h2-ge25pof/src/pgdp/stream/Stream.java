package pgdp.stream;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;


/**
 * Exception-fangende Stream Implementierung
 * <p>
 * Auf einer Stream-Instanz kann einmalig eine Stream-Methode aufgerufen werden,
 * danach ist diese Stream-Instanz an die entsprechende Operation gebunden bzw.
 * im fall einer terminalen Stream-Operation wird der Stream ausgewertet und
 * dabei konsumiert. Benutze Stream-Instanzen können daher nicht wiederverwendet
 * werden, es wird eine {@link IllegalStateException} geworfen.
 *
 * @param <T> der Typ der Elemente im Stream
 */
public interface Stream<T> {

    /**
     * Transformiert alle Elemente des Streams von T nach R mithilfe der übergeben
     * Function. Die Transformation hat auf fehlerhafte Elemente keinen Einfluss,
     * sie werden einfach weitergereicht.
     *
     * @param <R>    der Typ der Elemente des Streams nach map
     * @param mapper die Funktion, die die Elemente des Streams von Typ T (oder
     *               einer Oberklasse davon) zu Typ R (oder eine Unterklasse davon)
     *               umwandelt. Tritt dabei ein Fehler aus, so wird dieser als
     *               fehlerhaftes Element durch den Stream gereicht.
     * @return einen Stream vom Typ R
     * @throws NullPointerException falls mapper null ist
     */
    <R> Stream<R> map(Function<? super T, ? extends R> mapper);

    /**
     * Filtert die Elemente aus dem Stream, für die das übergebene Predicate false
     * zurückgibt. Der Filter hat auf fehlerhafte Elemente keinen Einfluss, sie
     * werden einfach weitergereicht.
     *
     * @param mapper die Bedingung, nach der Elemente im Stream weitergereicht (test
     *               liefert true) werden oder herausgefiltert (test liefert false).
     *               Tritt dabei ein Fehler aus, so wird dieser als fehlerhaftes
     *               Element durch den Stream gereicht und nicht herausgefiltert.
     * @return einen Stream vom Typ T
     * @throws NullPointerException falls filter null ist
     */
    Stream<T> filter(Predicate<? super T> filter);

    /**
     * Siehe {@link #map(Function)}, jedoch kann mapper beliebige Exceptions werfen.
     * <p>
     * <b>Diese Stream Operation verwandelt den Stream in einen "Checked
     * Stream".</b>
     *
     * @throws NullPointerException falls mapper null ist
     * @see #map(Function)
     */
    <R> Stream<R> mapChecked(ThrowingFunction<? super T, ? extends R> mapper);

    /**
     * Siehe {@link #filter(Predicate)}, jedoch kann filter beliebige Exceptions
     * werfen.
     * <p>
     * <b>Diese Stream Operation verwandelt den Stream in einen "Checked
     * Stream".</b>
     *
     * @throws NullPointerException falls filter null ist
     * @see #filter(Predicate)
     */
    Stream<T> filterChecked(ThrowingPredicate<? super T> filter);

    /**
     * Eliminiert Duplikate aus dem Stream, fehlerhafte Elemente sind davon nicht
     * betroffen. Die Reihenfolge der Elemente verändert sich dabei nicht, bei
     * gleichen Elementen wird nur das erste behalten; fehlerhafte Elemente werden
     * einfach in weitergereicht. Enthält der Stream ein null-Elemente, so werden
     * diese nicht anders als normale Elemente behandelt, der Ergebnis-Stream
     * enthält maximal ein null Element.
     * <p>
     * Beispiel: aus
     *
     * <pre>
     * [1, 3, 2, 2, null, 1, {fehlerhaft}, 3, {fehlerhaft}, 4]
     * </pre>
     *
     * wird
     *
     * <pre>
     * [1, 3, 2, null, {fehlerhaft}, {fehlerhaft}, 4]
     * </pre>
     *
     * @return einen Stream selben Typs, der frei von Duplikaten ist
     */
    Stream<T> distinct();

    /**
     * Gibt die Anzahl an Elementen im Stream zurück. Ist die Größe des Streams
     * bekannt, wird das Ergebnis sofort zurückgegeben, ohne das Elemente den Stream
     * durchlaufen.
     * <p>
     * Ist die Größe nicht bekannt, werden alle Elemente im Stream gezählt. Falls
     * Elemente davon fehlerhaft sind, wird eine ErrorsAtTerminalOperationException
     * geworfen. (Da z.B. nicht herausgefunden werden kann, ob diese Elemente bei
     * einer filter-Operation gefiltert werden müssten).
     * <p>
     * <b>Dies ist eine terminale Stream-Operation.</b>
     *
     * @return die Anzahl der Elemente im Stream
     * @throws ErrorsAtTerminalOperationException falls die exakte Anzahl unbekannt
     *                                            ist und ein fehlerhaftes Element
     *                                            verarbeitet werden müsste
     * @throws CheckedStreamException             falls der Stream an dem Punkt ein
     *                                            "Checked Stream" ist
     */
    long count();

    /**
     * Gibt das erste Element im Stream zurück.Ist der Stream leer, so wird
     * Optional.empty() zurückgegeben.
     * <p>
     * <b>Dies ist eine terminale Stream-Operation.</b>
     *
     * @return das erste Element im Stream, falls vorhanden.
     * @throws NullPointerException               falls das erste Element null ist
     * @throws ErrorsAtTerminalOperationException falls das erste Element fehlerhaft
     *                                            ist
     * @throws CheckedStreamException             falls der Stream an dem Punkt ein
     *                                            "Checked Stream" ist
     * @implSpec sobald ein Element gefunden wurde, werden keine weiteren Elemente
     *           von dem Stream verarbeitet.
     */
     Optional<T> findFirst();

    /**
     * Akkumuliert alle Elemente in dem Stream mit dem gegebenen accumulator. Ist
     * der Stream leer, so wird Optional.empty() zurückgegeben. Ist mindestens ein
     * Element vorhanden, so wird dieses als Startwert benutzt und alle darauf
     * folgenden werden mit dem Wert von davor kombiniert, woraus sich wieder ein
     * neuer Wert ergibt. Der akkumulierte Wert wird jeweils als erster Parameter
     * übergeben, der hinzuzufügende Wert an zweiter Stelle.
     * <p>
     * <b>Dies ist eine terminale Stream-Operation.</b>
     *
     * @param accumulator ein Akkumulator für Elemente vom Typ T
     * @return das Resultat aus der Akkumulation, falls vorhanden.
     * @throws NullPointerException               falls accumulator oder das
     *                                            Resultat null ist
     * @throws ErrorsAtTerminalOperationException falls irgendein Element fehlerhaft
     *                                            ist
     * @throws CheckedStreamException             falls der Stream an dem Punkt ein
     *                                            "Checked Stream" ist
     */
    Optional<T> reduce(BinaryOperator<T> accumulator);

    /**
     * Sammelt alle Elemente in einer Collection, die aus dem übergebenen
     * collectionGenerator neu erzeugt bzw. genommen wird.
     * <p>
     * <b>Dies ist eine terminale Stream-Operation.</b>
     *
     * @return das Resultat aus der Akkumulation, falls vorhanden.
     * @throws NullPointerException               falls der collectionGenerator oder
     *                                            die daraus kommende Collection
     *                                            null ist
     * @throws ErrorsAtTerminalOperationException falls irgendein Element fehlerhaft
     *                                            ist
     * @throws CheckedStreamException             falls der Stream an dem Punkt ein
     *                                            "Checked Stream" ist
     */
    Collection<T> toCollection(Supplier<? extends Collection<T>> collectionGenerator);

    /**
     * Transformiert alle fehlerhaften Elemente des Streams zu regulären, nicht
     * fehlerhaften Elementen mithilfe der übergebenen Function. Die Transformation
     * hat auf bereits nicht fehlerhafte Elemente keinen Einfluss, sie werden
     * einfach weitergereicht. Tritt bei errorMapper selbst ein Fehler auf, so
     * bleibt das Element fehlerhaft, und die aufgetretene Exception wird der Liste
     * hinzugefügt.
     * <p>
     * <b>Falls der Stream ein "Checked Stream" ist, ist es es danach nicht mehr</b>
     *
     * @param errorMapper die Funktion, die fehlerhafte Elemente des Streams zu
     *                    regulären umwandelt. Tritt bei errorMapper selbst ein
     *                    Fehler auf, so bleibt das Element fehlerhaft, und die neu
     *                    aufgetretene Exception wird dem Element hinzugefügt.
     * @return ein Stream selben Typs, garantiert kein "Checked Stream"
     * @throws NullPointerException falls errorMapper null ist
     */
    Stream<T> onErrorMap(Function<? super List<Exception>, ? extends T> errorMapper);

    /**
     * Siehe {@link #onErrorMap(Function)}, jedoch kann errorMapper beliebige
     * Exceptions werfen und falls der Stream ein "Checked Stream" ist, ändert sich
     * daran nichts.
     * <p>
     * <b>Diese Stream Operation verwandelt den Stream in einen "Checked
     * Stream".</b>
     *
     * @throws NullPointerException falls errorMapper null ist
     * @see #onErrorMap(Function)
     */
    Stream<T> onErrorMapChecked(ThrowingFunction<? super List<Exception>, ? extends T> errorMapper);

    /**
     * Filtert alle fehlerhaften Elemente aus dem Stream heraus.
     * <p>
     * <b>Falls der Stream ein "Checked Stream" ist, ist es es danach nicht mehr</b>
     *
     * @return ein Stream selben Typs, ohne fehlerhafte Elemente und garantiert kein
     *         "Checked Stream"
     */
    Stream<T> onErrorFilter();

    /**
     * Erzeugt einen neuen Stream, der den übergebenen Stream nutzt
     */
    static <T> Stream<T> of(java.util.stream.Stream<T> javaStream) {
        return new SourcePart<>(StreamIterator.of(javaStream),new StreamCharacteristics(OptionalLong.empty(),false,false));
    }

    /**
     * Erzeugt einen neuen Stream, der die Elemente der Collection enthält
     *
     * @throws NullPointerException falls die Collection null ist
     * @implSpec die Größe des erzeugten Streams ist bekannt und davon abhängige
     *           Operationen nutzten diese Information zur Optimierung
     */
    static <T> Stream<T> of(Collection<T> col) {
        return new SourcePart<>(StreamIterator.of(col),new StreamCharacteristics(OptionalLong.of(col.size()),false,false));
    }

    /**
     * Erzeugt einen neuen Stream, der die Elemente des Sets enthält
     *
     * @throws NullPointerException falls das Set null ist
     * @implSpec die Größe des erzeugten Streams und dessen Duplikat-Freiheit ist
     *           bekannt und davon abhängige Operationen nutzten diese Information
     *           zur Optimierung
     */
    static <T> Stream<T> of(Set<T> col) {
        return new SourcePart<>(StreamIterator.of(col),new StreamCharacteristics(OptionalLong.of(col.size()),false,false));
    }

    /**
     * Erzeugt einen neuen Stream, der die übergebenen Elemente enthält
     *
     * @throws NullPointerException falls das Array null ist
     * @implSpec die Größe des erzeugten Streams ist bekannt und davon abhängige
     *           Operationen nutzten diese Information zur Optimierung
     */
    @SafeVarargs
    static <T> Stream<T> of(T... elements) {
        return new SourcePart<>(StreamIterator.of(elements),new StreamCharacteristics(OptionalLong.of(elements.length),false,false));
    }
}

/**
 * local class for StreamElement
 * 
 * @param <T>
 */
 final class StreamElement<T> {
    // * local attributes
    final T elem;
    final List<Exception> exceptions;

    // * local constructor
    StreamElement(T t) {
        elem = t;
        exceptions = new ArrayList<>();
    }

    StreamElement(T t, List<Exception> exceptions) {
        elem = t;
        this.exceptions = exceptions;
    }

    // *getters
    public T getElem() {
        return this.elem;
    }

    public List<Exception> getExceptions() {
        return this.exceptions;
    }

    // * instance functions
    boolean hasExceptions() {
        return exceptions.size() > 0;
    }

    boolean isRegular() {
        return exceptions.size() == 0;
    }

    boolean isFaulty() {
        return elem == null;
    }

    /**
     * focus only on the hashcode of the element
     */
    @Override
    public int hashCode() {
        if(elem!=null)
            return elem.hashCode();
        else return 404;
    }

    @Override
    public boolean equals(Object obj) {
        if(elem!=null)
            return elem.equals(obj);
        else return false;
    }

    @Override
    public String toString() {
        String type = isFaulty()? "faulty":"regular";
        return "StreamElement("+type+")"+"{" +
                "elem=" + elem +
                ", exceptions=" + exceptions +
                '}';
    }

    /**
     * creating a new instance of StreamElement since this class is immutable, copy
     * the element and lists of exceptions and adding the given new exception e
     * 
     * @param <R>
     * @param e
     * @return
     */
    <R> StreamElement<R> withExceptionAdded(Exception e) {
        //!!! copy the current exceptions
        List<Exception> newList = new ArrayList<>(this.exceptions);
        // adding the new exception
        newList.add(e); 

        //!!! if this element is faulty, then we don't need to cast the element since the element is null
        if(isFaulty()){
            return new StreamElement<R>(null,newList);
        }
       
        //!!! otherwise this element is regular
        //!!! since this element will become faulty too, we don't need to cast the element neither since it will be null
        return new StreamElement<R>(null, newList);
    }

    /**
     * cast the type of element (T) to R without adding a new exception. If the
     * stream element is regular, then throw an Exception //??? what kind of
     * exception to throw?
     * 
     * @param <R>
     * @return
     */
    <R> StreamElement<R> tryAdapt() {
        // if regular => exception
        if (isRegular()) {
            try {
                throw new Exception("regular stream element can't tryAdapt");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // otherwise it's a faulty element
        return new StreamElement<R>(null,this.exceptions);
    }


    //* class functions
    static <T> StreamElement<T> of(T t){
        return new StreamElement<T>(t);
    }
}


/**
 * local interface for stream iterator
 * @param <T>
 */
interface StreamIterator<T>{


    abstract boolean hasNext();
    abstract StreamElement<T> next();
    abstract OptionalLong getSize();

    /**
     * transform the given collection to stream iterator
     * @param <T>
     * @param c
     * @return
     */
    static <T> StreamIterator<T> of(Collection<T> c){
        Iterator<T> iter = c.iterator();
        return new StreamIterator<T>(){
                @Override
                public StreamElement<T> next() {
                    T tmp = iter.next();
                    return new StreamElement<>(tmp);
                }
        
                @Override
                public boolean hasNext() {
                    return iter.hasNext();
                }
        
                @Override
                public OptionalLong getSize() {
                    return OptionalLong.of(c.size());
                }
        };
    }

    /**
     * transform an iterable to stream iterator
     * @param <T>
     * @param c
     * @return
     */
    static <T> StreamIterator<T> of(Iterable<T> c){
        Iterator<T> iter = c.iterator();

        return new StreamIterator<T>(){

            @Override
            public boolean hasNext() {
                return iter.hasNext();
            }

            @Override
            public StreamElement<T> next() {
                T next = iter.next();
                return new StreamElement<T>(next);
            }

            @Override
            public OptionalLong getSize() {
                return OptionalLong.empty();
            }
        };
    }


    /**
     * transform the given array to stream iterator
     * @param <T>
     * @return
     */
    static <T> StreamIterator<T> of(T[] c){
        int index[] = new int[1];       //!!! initialization on heap
        return new StreamIterator<T>(){

            @Override
            public boolean hasNext() {
                return index[0] < c.length;
            }

            @Override
            public StreamElement<T> next() {
                T tmp = c[index[0]++];
                return new StreamElement<T>(tmp);
            }

            @Override
            public OptionalLong getSize() {
                return OptionalLong.of(c.length);
            }
        };
    }


    /**
     * transform the given stream to StreamIterator
     */
    static <T> StreamIterator<T> of(java.util.stream.Stream<T> c){
        Iterator<T> iter = c.iterator();

        return new StreamIterator<T>() {
            @Override
            public boolean hasNext() {
                return iter.hasNext();
            }

            @Override
            public StreamElement<T> next() {
                T next = iter.next();
                return new StreamElement<>(next);
            }

            @Override
            public OptionalLong getSize() {
                return OptionalLong.empty();
            }
        };
    }
}
 

/**
 * local class for characteristics of the stream
 */
final class StreamCharacteristics{
    OptionalLong streamSize;
    boolean isDistinct;
    boolean isChecked;

    StreamCharacteristics(OptionalLong streamSize, boolean isDistinct, boolean isChecked) {
        this.streamSize = streamSize;
        this.isDistinct = isDistinct;
        this.isChecked = isChecked;
    }


    //* common case: unknown stream size, not distinct and not checked
    static StreamCharacteristics common_case = new StreamCharacteristics(OptionalLong.empty(), false, false);

    //*getters
    OptionalLong getStreamSize() {
        return streamSize;
    }

    boolean isDistinct() {
        return isDistinct;
    }

    boolean isChecked() {
        return isChecked;
    }

    //* class functions
    static StreamCharacteristics regular(){
        return common_case;
    }

    //* instance functions
    //!!! the "wither" functions create a new object of StreamCharacteristics with the specified new attribute, the other attributes remain the same
    StreamCharacteristics withStreamSize(long size){
        StreamCharacteristics ret = new StreamCharacteristics(OptionalLong.of(size), isDistinct, isChecked);
        return ret;
    }

    StreamCharacteristics withDistinct(boolean distinct){
        StreamCharacteristics ret = new StreamCharacteristics(this.streamSize, distinct,this.isChecked);
        return ret;
    }

    StreamCharacteristics withChecked(boolean checked){
        StreamCharacteristics ret = new StreamCharacteristics(this.streamSize, this.isDistinct,checked);
        return ret;
    }
}


/**
 * local interface for StreamOpration
 * @param <T>
 */
interface StreamOperation<T>{
    void start(StreamCharacteristics upstreamCharacteristics);
    void acceptElement(StreamElement<T> elem);
    void finish();
    boolean needsMoreElements();
}

interface TerminalStreamOperation<T, R> extends StreamOperation<T>, Supplier<R>{

}


/**
 * define a stream operation
 * @param <T>
 */
@FunctionalInterface
interface StreamOperable<T>{
    StreamOperation<T> getStreamOperation();
}

abstract class AbstractStreamPart<IN, OUT> implements Stream<OUT>, StreamOperable<IN>{
    StreamOperable<OUT> next;

    
    /**
     * if next is null, set it to out, otherwise throw an exception
     * @param out
     */
    void setNext(StreamOperable<OUT> out) throws IllegalStateException{
        if(next ==null) next = out;
        else throw new IllegalStateException();
    }

    @Override
    public <R> Stream<R> map(Function<? super OUT, ? extends R> mapper) {
        return null;
    }

    @Override
    public Stream<OUT> filter(Predicate<? super OUT> filter) {
        return null;
    }

    @Override
    public <R> Stream<R> mapChecked(ThrowingFunction<? super OUT, ? extends R> mapper) {
        return null;
    }

    @Override
    public Stream<OUT> filterChecked(ThrowingPredicate<? super OUT> filter) {
        return null;
    }

    @Override
    public Stream<OUT> distinct() {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public Optional<OUT> findFirst() {
        return Optional.empty();
    }

    @Override
    public Optional<OUT> reduce(BinaryOperator<OUT> accumulator) {
        return Optional.empty();
    }

    @Override
    public Collection<OUT> toCollection(Supplier<? extends Collection<OUT>> collectionGenerator) {
       return null;

    }

    @Override
    public Stream<OUT> onErrorMap(Function<? super List<Exception>, ? extends OUT> errorMapper) {
        return null;
    }

    @Override
    public Stream<OUT> onErrorMapChecked(ThrowingFunction<? super List<Exception>, ? extends OUT> errorMapper) {
        return null;
    }

    @Override
    public Stream<OUT> onErrorFilter() {
        return null;
    }

    @Override
    public StreamOperation<IN> getStreamOperation() {
        return null;
    }

    abstract SourcePart<?> getSource();
}

class SourcePart<T> extends AbstractStreamPart<T,T>{
    //* attrs
    private StreamIterator<T> iterator;
    private StreamCharacteristics characteristics;

    SourcePart(StreamIterator<T> iterator, StreamCharacteristics characteristics) {
        this.iterator = iterator;
        this.characteristics = characteristics;
    }

    @Override
    SourcePart<?> getSource() {
        return this;        //???
    }

    @Override
    public StreamOperation<T> getStreamOperation() {
        return super.getStreamOperation();
    }

    @Override
    public Collection<T> toCollection(Supplier<? extends Collection<T>> collectionGenerator) {
        Collection<T> tmp = collectionGenerator.get();

        //* collect the elements pointed by the iterator
        while (iterator.hasNext()){
            tmp.add(iterator.next().elem);
        }

        return tmp;

    }

    @Override
    public long count() {
        //!!! if there are checked exceptions => throw an exception
        if(characteristics.isChecked){
            throw new ErrorsAtTerminalOperationException();
        }

        //!!! if the size is already known => return it immediately
        if(characteristics.streamSize!=OptionalLong.empty()){
            return characteristics.streamSize.getAsLong();
        }

        //!!! otherwise loop the elements
        long size = 0;
        while (iterator.hasNext()){
            iterator.next();
            size++;
        }
        return size;
    }

    @Override
    public Stream<T> filter(Predicate<? super T> filter) {
        ArrayList<T> tmp = new ArrayList<>();

        //* iterate the stream to find elements that pass the filter
        //!!! pass the fehlerhaft SE further
        while (iterator.hasNext()){
            StreamElement<T> streamElement = iterator.next();
            T elem = streamElement.elem;
            if(streamElement.isFaulty() ||elem == null ||filter.test(elem)){
                tmp.add(elem);
            }
        }

        return new SourcePart<T>(StreamIterator.of(tmp),new StreamCharacteristics(OptionalLong.of(tmp.size()),false,false));
    }

    @Override
    public <R> Stream<R> map(Function<? super T, ? extends R> mapper) {
        //!!! no affect on faulty SE
        ArrayList<R> tmp = new ArrayList<>();

        //* iterate the stream to map each element
        while (iterator.hasNext()){
            StreamElement<T> streamElement = iterator.next();
            T elem = streamElement.elem;

            //!!! pass the fehlerhaft SE further
            if(streamElement.isFaulty()){
                tmp.add(null);
            }

            //!!! otherwise do the mapping
            else{
                R mapped = null;
                try {
                    mapped = mapper.apply(elem);
                }catch (Exception e){ }
                tmp.add(mapped);
            }
        }
        return new SourcePart<R>(StreamIterator.of(tmp),new StreamCharacteristics(OptionalLong.of(tmp.size()),false,false));
    }

    /**
     * try to return the first element of the stream
     * @return
     */
    @Override
    public Optional<T> findFirst() {
        if(!iterator.hasNext()) return Optional.empty();

        else {
            T first = iterator.next().elem;
            return Optional.of(first);
        }
    }

    /**
     * remove the duplicates of the stream, keep only the first value that have duplicates in the stream
     * <p>
     *     Faulty SE will be ignored;
     *     null value will be treated as normal value
     * </p>
     * @return
     */
    @Override
    public Stream<T> distinct() {
        ArrayList<T> tmp = new ArrayList<>();
        while (iterator.hasNext()){
            StreamElement<T> streamElement = iterator.next();
            T elem = streamElement.elem;

            //!!! if stream element is faulty => do nothing
            if(streamElement.isFaulty()) {}

            //!!! else , add it to tmp
            else {
                tmp.add(elem);
            }
        }

        ArrayList<Integer> indexes = new ArrayList<>();

        //* removing duplicates
        for (int i = 0; i < tmp.size(); i++) {
            T p1 = tmp.get(i);

            //* iterating the following elements since p1
            for (int j = i+1; j < tmp.size(); j++) {
                if((p1==null && tmp.get(j)==null)||(p1!=null && p1.equals(tmp.get(j)))){
                    if(!indexes.contains(j))
                        indexes.add(j);
                }
            }
        }
        indexes.sort((i1,i2)->i1-i2);

        for (int i = 0; i < indexes.size(); i++) {
            tmp.remove(indexes.get(i)-i);
        }


        // return
        return new SourcePart<T>(StreamIterator.of(tmp),new StreamCharacteristics(OptionalLong.of(tmp.size()),false,false));
    }

    /**
     * only map the faulty elements
     * @param errorMapper
     * @return
     */
    @Override
    public Stream<T> onErrorMap(Function<? super List<Exception>, ? extends T> errorMapper) {
        //!!! no affect on regular SE
        ArrayList<T> tmp = new ArrayList<T>();

        //* iterate the stream to map each element
        while (iterator.hasNext()){
            StreamElement<T> streamElement = iterator.next();
            T elem = streamElement.elem;

            //!!! if fehlerhaft
            if(streamElement.isFaulty()){
                T mapped = null;
                try {
                    mapped = errorMapper.apply(streamElement.exceptions);
                }catch (Exception e){ }
                tmp.add(mapped);
            }

            //!!! pass the regular SE further
            else{
               tmp.add(elem);
            }
        }
        return new SourcePart<T>(StreamIterator.of(tmp),new StreamCharacteristics(OptionalLong.of(tmp.size()),false,false));
    }

    @Override
    public <R> Stream<R> mapChecked(ThrowingFunction<? super T, ? extends R> mapper) {
        //!!! no affect on faulty SE
        ArrayList<R> tmp = new ArrayList<>();

        //* iterate the stream to map each element
        while (iterator.hasNext()){
            StreamElement<T> streamElement = iterator.next();
            T elem = streamElement.elem;

            //!!! pass the fehlerhaft SE further
            if(streamElement.isFaulty()){
                tmp.add(null);
            }

            //!!! otherwise do the mapping
            else{
                R mapped = null;
                try {
                    mapped = mapper.apply(elem);
                }catch (Exception e){ }
                tmp.add(mapped);
            }
        }
        return new SourcePart<R>(StreamIterator.of(tmp),new StreamCharacteristics(OptionalLong.of(tmp.size()),false,true));
    }

    /**
     * filter out all the faulty SE
     * @return
     */
    @Override
    public Stream<T> onErrorFilter() {
        ArrayList<T> tmp = new ArrayList<>();

        //* iterate the stream to find elements that are faulty
        //!!! pass the regular SE further
        while (iterator.hasNext()){
            StreamElement<T> streamElement = iterator.next();
            T elem = streamElement.elem;
            if(streamElement.isRegular()){
                tmp.add(elem);
            }
        }

        return new SourcePart<T>(StreamIterator.of(tmp),new StreamCharacteristics(OptionalLong.of(tmp.size()),false,false));
    }


    @Override
    public Stream<T> filterChecked(ThrowingPredicate<? super T> filter) {
        ArrayList<T> tmp = new ArrayList<>();

        //* iterate the stream to find elements that pass the filter
        //!!! pass the fehlerhaft SE further
        while (iterator.hasNext()){
            StreamElement<T> streamElement = iterator.next();
            T elem = streamElement.elem;
            try {
                if(streamElement.isFaulty() ||elem == null ||filter.test(elem)){
                    tmp.add(elem);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return new SourcePart<T>(StreamIterator.of(tmp),new StreamCharacteristics(OptionalLong.of(tmp.size()),false,false));

    }

    @Override
    public Stream<T> onErrorMapChecked(ThrowingFunction<? super List<Exception>, ? extends T> errorMapper) {
        //!!! no affect on regular SE
        ArrayList<T> tmp = new ArrayList<T>();

        //* iterate the stream to map each element
        while (iterator.hasNext()){
            StreamElement<T> streamElement = iterator.next();
            T elem = streamElement.elem;

            //!!! if fehlerhaft
            if(streamElement.isFaulty()){
                T mapped = null;
                try {
                    mapped = errorMapper.apply(streamElement.exceptions);
                }catch (Exception e){ }
                tmp.add(mapped);
            }

            //!!! pass the regular SE further
            else{
                tmp.add(elem);
            }
        }
        return new SourcePart<T>(StreamIterator.of(tmp),new StreamCharacteristics(OptionalLong.of(tmp.size()),false,false));
    }


    @Override
    public Optional<T> reduce(BinaryOperator<T> accumulator) {
        //!!! if the stream is empty => return empty
        if(!iterator.hasNext()) return Optional.empty();

        //!!! else
        else{
            T ACC = iterator.next().elem;

            while (iterator.hasNext()){
                T next = iterator.next().elem;
                ACC = accumulator.apply(ACC,next);
            }

            return Optional.of(ACC);
        }
    }
}

