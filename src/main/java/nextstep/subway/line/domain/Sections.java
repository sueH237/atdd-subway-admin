package nextstep.subway.line.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;

import nextstep.subway.station.domain.Station;

@Embeddable
public class Sections {

    @OneToMany(mappedBy = "line", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Section> sections = new ArrayList<Section>();

    
    protected Sections() {
    }

    private Sections(List<Section> sections) {
        this.sections = sections;
    }
    
    public static Sections from(List<Section> sections) {
        return new Sections(sections);
    }

    public List<Station> getStations() {
        return sections.stream()
                .flatMap(section -> Stream.of(section.getUpStation(), section.getDownStation()))
                .distinct()
                .collect(Collectors.toList());
    }

    public void add(Section section) {
        sections.add(section);
    }
    
    public int count() {
        return sections.size();
    }
    

}
