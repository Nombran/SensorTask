package by.bsuir.sensor.sensor;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class SensorHateoasUtil {
    public void createPaginationLinks(PagedModel<SensorDto> model, String textPart) {
        PagedModel.PageMetadata metadata = model.getMetadata();
        int curPage = (int) metadata.getNumber();
        int size = (int) metadata.getSize();
        int totalPages = (int) metadata.getTotalPages();
        if (curPage < totalPages) {
            String nextPageHref = linkTo(methodOn(SensorController.class)
                    .findSensors(curPage + 1, size, textPart))
                    .toUriComponentsBuilder()
                    .toUriString();
            nextPageHref = nextPageHref.replaceAll("\\{.*?}", "");
            Link nextPage = Link.of(nextPageHref, "next");
            String lastPageHref = linkTo(methodOn(SensorController.class)
                    .findSensors(curPage + 1, size, textPart))
                    .toUriComponentsBuilder()
                    .toUriString();
            lastPageHref = lastPageHref.replaceAll("\\{.*?}", "");
            Link lastPage = Link.of(lastPageHref, "last");
            model.add(nextPage, lastPage);
        }
        if (curPage > 1) {
            String prevPageHref = linkTo(methodOn(SensorController.class)
                    .findSensors(curPage + 1, size, textPart))
                    .toUriComponentsBuilder()
                    .toUriString();
            prevPageHref = prevPageHref.replaceAll("\\{.*?}", "");
            Link prevPage = Link.of(prevPageHref, "prev");
            model.add(prevPage);
        }
        String selfRelHref = linkTo(methodOn(SensorController.class)
                .findSensors(curPage + 1, size, textPart))
                .toUriComponentsBuilder()
                .toUriString();
        selfRelHref = selfRelHref.replaceAll("\\{.*?}", "");
        Link selfRel = Link.of(selfRelHref);
        model.add(selfRel);
        model.getContent().forEach(this::createSelfRelLink);
    }

    public SensorDto createSelfRelLink(SensorDto sensor) {
        long id = sensor.getId();
        sensor.add(linkTo(methodOn(SensorController.class)
                .findById(id))
                .withSelfRel());
        return sensor;
    }
}
