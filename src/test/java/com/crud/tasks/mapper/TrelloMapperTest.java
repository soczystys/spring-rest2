package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TrelloMapperTest {

    @Autowired
    private TrelloMapper trelloMapper;

    @Test
    public void mapToBoards() {
        List<TrelloBoardDto> listDto = new ArrayList<>();
        listDto.add(new TrelloBoardDto("id1", "name1", new ArrayList<>()));
        listDto.add(new TrelloBoardDto("id2", "name2", new ArrayList<>()));
        listDto.add(new TrelloBoardDto("id3", "name3", new ArrayList<>()));

        List<TrelloBoard> list = trelloMapper.mapToBoards(listDto);
        assertEquals(3, list.size());
        assertEquals("id2", list.get(1).getId());
        assertEquals("name2", list.get(1).getName());
        assertEquals(0, list.get(1).getLists().size());
    }

    @Test
    public void mapToBoardsDto() {
        List<TrelloBoard> list = new ArrayList<>();
        list.add(new TrelloBoard("id1", "name1", new ArrayList<>()));
        list.add(new TrelloBoard("id2", "name2", new ArrayList<>()));
        list.add(new TrelloBoard("id3", "name3", new ArrayList<>()));

        List<TrelloBoardDto> listDto = trelloMapper.mapToBoardsDto(list);
        assertEquals(3, listDto.size());
        assertEquals("id2", listDto.get(1).getId());
        assertEquals("name2", listDto.get(1).getName());
        assertEquals(0, listDto.get(1).getLists().size());
    }

    @Test
    public void mapToList() {
        List<TrelloListDto> listDto = new ArrayList<>();
        listDto.add(new TrelloListDto("id", "name", true));
        List<TrelloList> list = trelloMapper.mapToList(listDto);

        assertEquals(1, list.size());
        assertEquals("id", list.get(0).getId());
        assertEquals("name", list.get(0).getName());
        assertTrue(list.get(0).isClosed());
    }

    @Test
    public void mapToListDto() {
        List<TrelloList> list = new ArrayList<>();
        list.add(new TrelloList("id", "name", true));
        List<TrelloListDto> listDto = trelloMapper.mapToListDto(list);

        assertEquals(1, listDto.size());
        assertEquals("id", listDto.get(0).getId());
        assertEquals("name", listDto.get(0).getName());
        assertTrue(listDto.get(0).isClosed());
    }

    @Test
    public void mapToCardDto() {
        TrelloCard trelloCard = new TrelloCard("name",
                "description", "pos", "listId");
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        assertEquals("name", trelloCardDto.getName());
        assertEquals("description", trelloCardDto.getDescription());
        assertEquals("pos", trelloCardDto.getPos());
        assertEquals("listId", trelloCardDto.getListId());
    }

    @Test
    public void mapToCard() {
        TrelloCardDto trelloCardDto = new TrelloCardDto("name",
                "description", "pos", "listId");
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);

        assertEquals("name", trelloCard.getName());
        assertEquals("description", trelloCard.getDescription());
        assertEquals("pos", trelloCard.getPos());
        assertEquals("listId", trelloCard.getListId());
    }
}